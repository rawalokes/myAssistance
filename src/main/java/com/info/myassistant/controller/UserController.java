package com.info.myassistant.controller;

import com.info.myassistant.dto.ChangePasswordDto;
import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.dto.UserDto;
import com.info.myassistant.model.Users;
import com.info.myassistant.serviceimpl.UserServiceImpl;
import com.info.myassistant.utility.GetCurrentUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * @author rawalokes
 * Date:3/25/22
 * Time:6:51 AM
 */
@Controller
public class UserController {
    private final UserServiceImpl userService;
    private final GetCurrentUserDetails currentUserDetails;

    public UserController(UserServiceImpl userService, GetCurrentUserDetails currentUserDetails) {
        this.userService = userService;
        this.currentUserDetails = currentUserDetails;
    }

    /**
     *
     * @return login page
     */
    @GetMapping("/login")
    public String userLogin() {
        return "users/login";
    }

    /**
     *
     * @param model
     * @return user register page
     */
    @GetMapping("/register")
    public String getCreateUSer(Model model) {
        model.addAttribute("userDetails", new UserDto());
        return "users/createUser";
    }

    /**
     *
     * @param userDto
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/register")
    public String postCreateUser(@Valid @ModelAttribute("userDetails") UserDto userDto
            , BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "users/createUser";
        }
        //call service to create user
        ResponseDto responseDto = userService.create(userDto);
        //check responseDto status is true and redirect to view all user
        if (responseDto.isStatus()) {
            return "redirect:/register?created";
            //if responseDto status is false return create user form with error message
        } else {
            model.addAttribute("errorMessage", responseDto.getMessage());
            return "users/createUser";
        }

    }

    @GetMapping("/change-password")
    public String getChangePassword(Model model) {
        model.addAttribute("passwordDetail", new ChangePasswordDto());
        return "users/changePassword";
    }

    @PostMapping("/change-password")
    public String postChangePassword(@Valid @ModelAttribute("passwordDetail") ChangePasswordDto changePasswordDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "users/changePassword";
        }

        UserDto userDto = new UserDto(changePasswordDto);
        Users users = currentUserDetails.getCurrentUser();
        userDto.setUserId(users.getId());
        ResponseDto responseDto = userService.create(userDto);

        if (responseDto.isStatus()) {
            return "redirect:/change-password?changed";
        } else {
            model.addAttribute("errorMessage", responseDto.getMessage());
            System.out.println(responseDto.getMessage());
            return "users/changePassword";
        }
    }

}
