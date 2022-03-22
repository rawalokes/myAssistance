package com.info.myassistant.dto.userDto;

import com.info.myassistant.model.user.User;
import lombok.*;

import javax.persistence.*;

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
    private String name;
    private String email;
    private String password;


    public UserDto(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}

