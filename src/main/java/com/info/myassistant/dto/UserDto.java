package com.info.myassistant.dto;

import com.info.myassistant.model.Users;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

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
    @NotEmpty(message = "Email cannot be empty")
    private String email;

//    @Size(min = 8,message = "Password must of minimum 8 character")
//    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",message = "Password must consist of one uppercase , lowercase ,special character and number")
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    private String confirmPassword;


    private String role;


    public UserDto(Users users) {
        this.userId = users.getId();
        this.name = users.getName();
        this.email = users.getEmail();
        this.password = users.getPassword();
//        this.role=user.getRole();
    }
    public UserDto(ChangePasswordDto changePasswordDto){
        this.password=changePasswordDto.getPassword();
        this.confirmPassword=changePasswordDto.getConfirmPassword();
    }
}

