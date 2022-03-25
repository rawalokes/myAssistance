package com.info.myassistant.controller;

import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.dto.UserDto;
import com.info.myassistant.serviceimpl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author rawalokes
 * Date:3/25/22
 * Time:6:51 AM
 */
@Controller
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/create")
    public String getCreateUSer(Model model) {
        model.addAttribute("userDetails", new UserDto());
        return "users/createUser";
    }

    @PostMapping("/create")
    public String postCreateUser(@Valid @ModelAttribute("userDetails") UserDto userDto
            , BindingResult bindingResult, Model model) {
        ResponseDto responseDto = userService.create(userDto);
        if (bindingResult.hasErrors()){
            return "users/createUser";
        }
        if (responseDto.isStatus()) {
            return "users/login";
        } else {
            model.addAttribute("errorMessage", responseDto.getMessage());
            return "users/createUser";
        }

    }

}
