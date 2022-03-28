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

    /**
     * find current user and return
     * @return current user
     */
    public  Users getCurrentUser(){
        //get login users email
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        //find login user based on email address and  return login user object
        return userRepo.findByEmail(username);
    }
}
