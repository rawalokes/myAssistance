package com.info.myassistant.model;

import com.info.myassistant.dto.UserDto;
import lombok.*;

import javax.persistence.*;

/**
 * @author rawalokes
 * Date:3/22/22
 * Time:4:38 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq", allocationSize = 1)
    private Integer userId;
    private String name;
    private String email;
    private String password;


    public User(UserDto userDto) {
        this.userId = userDto.getUserId();
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
    }
}
