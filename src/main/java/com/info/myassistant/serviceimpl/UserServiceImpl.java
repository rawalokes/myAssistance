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
            if (userDto.getUserId() == null) {
                String password = PasswordGenerator.password();
                Users users = new Users(userDto);
                sendEmail.sendEmail(users.getEmail(), users.getName(), password);
                users.setPassword(bCryptPasswordEncoder.encode(password));
                userRepo.save(users);
                return successResponse("Users register successfully", null);
            } else {

                if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
                    System.out.println(userDto.getPassword());
                    System.out.println(userDto.getConfirmPassword());
                    System.out.println(userDto.getPassword().equals(userDto.getConfirmPassword()));
                    return errorResponse("Password do not match", null);
                }
                Users currentUser = currentUserDetails.getCurrentUser();
                userDto.setEmail(currentUser.getEmail());
                userDto.setName(currentUser.getName());
                Users users = new Users(userDto);
                users.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

                sendEmail.sendEmail(userDto.getEmail(), users.getName(), "changed");
                userRepo.save(users);
                return successResponse("Password Changed", null);
            }
        } catch (Exception e) {
            return errorResponse("Email already in use", null);

        }    }


    @Override
    public ResponseDto findByID(Integer integer) {
        Optional<Users> user = userRepo.findById(integer);
        if (user.isPresent()) {
            return successResponse("", user.get());
        } else {
            return errorResponse("Users Not Found", null);
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            Users users = userRepo.findByEmail(username);
            if (users == null) {
                errorResponse("user not found", null);
            }
            return new org.springframework.security.core.userdetails.User(users.getEmail(), users.getPassword(), mapRolesToAuthorities(users.getRoles()));

        } catch (InternalAuthenticationServiceException e) {
            e.printStackTrace();
            return null;
        }

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
