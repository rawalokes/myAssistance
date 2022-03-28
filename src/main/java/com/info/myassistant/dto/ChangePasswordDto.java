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
public class ChangePasswordDto {
    @Size(min = 8,message = "Password must of minimum 8 character")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",message = "Password must consist of one uppercase , lowercase ,special character and number")
    private String password;

    @Size(min = 8,message = "Password must of minimum 8 character")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",message = "Password must consist of one uppercase , lowercase ,special character and number")
    private String confirmPassword;
    private String role;


}

