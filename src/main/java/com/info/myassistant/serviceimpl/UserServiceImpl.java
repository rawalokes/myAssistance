package com.info.myassistant.serviceimpl;

import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.dto.UserDto;
import com.info.myassistant.model.Role;
import com.info.myassistant.model.Users;
import com.info.myassistant.repo.UserRepo;
import com.info.myassistant.service.UserService;
import com.info.myassistant.shared.BaseResponse;
import com.info.myassistant.utility.PasswordGenerator;
import com.info.myassistant.utility.SendEmail;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public UserServiceImpl(@Lazy BCryptPasswordEncoder bCryptPasswordEncoder
            ,@Lazy UserRepo userRepo,SendEmail sendEmail) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.sendEmail=sendEmail;
        this.userRepo = userRepo;
    }

    @Override
    public ResponseDto create(UserDto userDto) {
        try {
            String password=PasswordGenerator.password();
//            userDto.setPassword(password);
//            userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            Users users = new Users(userDto);
            sendEmail.sendEmail(users.getEmail(),users.getName(),password,false);
            users.setPassword(bCryptPasswordEncoder.encode(password));
            userRepo.save(users);

            return successResponse("Users Register Successfully", null);
        } catch (Exception e) {
            System.out.println(e);
            return errorResponse("Email already in use", null);

        }
    }

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
            return new org.springframework.security.core.userdetails.User(users.getEmail()
                    , users.getPassword(), mapRolesToAuthorities(users.getRoles()));

        }catch (InternalAuthenticationServiceException e){
            e.printStackTrace();
            return null;
        }

    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
