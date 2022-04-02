package com.info.myassistant.serviceimpl;

import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.dto.UserDto;
import com.info.myassistant.model.Role;
import com.info.myassistant.model.Users;
import com.info.myassistant.repo.UserRepo;
import com.info.myassistant.service.UserService;
import com.info.myassistant.shared.BaseResponse;
import com.info.myassistant.utility.GetCurrentUserDetails;
import com.info.myassistant.utility.PasswordGenerator;
import com.info.myassistant.utility.SendEmail;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author rawalokes
 * Date:3/22/22
 * Time:4:58 PM
 */
@Service
public class UserServiceImpl extends BaseResponse implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepo userRepo;
    private final SendEmail sendEmail;
    private final GetCurrentUserDetails currentUserDetails;

    public UserServiceImpl(@Lazy BCryptPasswordEncoder bCryptPasswordEncoder, @Lazy UserRepo userRepo, SendEmail sendEmail, GetCurrentUserDetails currentUserDetails) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.sendEmail = sendEmail;
        this.userRepo = userRepo;
        this.currentUserDetails = currentUserDetails;
    }

    @Override
    public ResponseDto create(UserDto userDto) {

        try {
            //check if user is trying to register or updating his/her details
            if (userDto.getUserId() == null) {
                //generate 8 digit random password
                String password = PasswordGenerator.password();
                //convert user dto into users
                Users users = new Users(userDto);
                //set generated password as user password and encrypt it
                users.setPassword(bCryptPasswordEncoder.encode(password));
                userRepo.save(users);
                //message to be sent to newly registered user
                String message="Please change the password" +
                        "\nClick on Assistant to change password";
                ///send email with password and please change password message
                sendEmail.sendEmail(users.getEmail(), users.getName(), password,message);
                return successResponse("Users register successfully", null);
            }
            //if user is try his/her update its password
            else {
                //check if user enter same password twice
                if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
                    return errorResponse("Password do not match", null);
                }
                //get currently log in user
                Users currentUser = currentUserDetails.getCurrentUser();

                userDto.setEmail(currentUser.getEmail());
                userDto.setName(currentUser.getName());

                Users users = new Users(userDto);
                users.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
                //send email saying password change
                // and message is null as no need to say please change password for change password
                sendEmail.sendEmail(userDto.getEmail(), users.getName(), "changed","");
                userRepo.save(users);
                return successResponse("Password Changed", null);
            }
        } catch (Exception e) {
            //if unique constrain exception occur
            return errorResponse("Email already in use", null);

        }
    }



    @Override
    public ResponseDto findByID(Integer integer) {
        //find user by id
        Optional<Users> user = userRepo.findById(integer);
        if (user.isPresent()) {
            //if user is present return user
            return successResponse("", user.get());
        } else {
            return errorResponse("Users Not Found", null);
        }

    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            Users users = userRepo.findByEmail(username);
            if (users == null){
                return null;
            }
            return new org.springframework.security.core.userdetails.User(users.getEmail()
                    , users.getPassword(), mapRolesToAuthorities(users.getRoles()));
        } catch (InternalAuthenticationServiceException e) {

            e.printStackTrace();
            return null;
        }

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}
