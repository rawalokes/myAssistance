package com.info.myassistant.dto;

import com.info.myassistant.model.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author rawalokes
 * Date:3/22/22
 * Time:4:46 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {

    private Integer userId;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3,message = "Invalid name")
    @Pattern(regexp = "[a-zA-Z]+",message = "Name cannot have special character or number")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @Size(min = 8,message = "Password must of minimum 8 character")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",message = "Password must consist of one uppercase , lowercase ,special character and number")
    private String password;


    public UserDto(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}

