package com.info.myassistant.model;

import com.info.myassistant.dto.UserDto;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
@Table(name = "tbl_user",uniqueConstraints = {
        @UniqueConstraint(name = "uk_user_email",columnNames = "email")
})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq", allocationSize = 1)
    private Integer id;
    private String name;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "id", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_role_id"))
    private List<Role> roles;

    public Users(UserDto userDto) {
        this.id = userDto.getUserId();
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        this.roles= Arrays.asList(new Role("ROLE_USER"));

    }
}
