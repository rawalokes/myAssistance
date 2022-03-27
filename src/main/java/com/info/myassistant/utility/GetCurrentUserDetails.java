package com.info.myassistant.utility;

import com.info.myassistant.model.Users;
import com.info.myassistant.repo.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author rawalokes
 * Date:3/27/22
 * Time:1:31 AM
 */
@Component
public class GetCurrentUserDetails {
    private final UserRepo userRepo;

    public GetCurrentUserDetails(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public  Users getCurrentUser(){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByEmail(username);
    }
}
