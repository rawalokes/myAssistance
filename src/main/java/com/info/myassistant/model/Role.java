package com.info.myassistant.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author rawalokes
 * Date:3/26/22
 * Time:4:48 PM
 */
@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
@Builder
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "role_seq_gen")
    @SequenceGenerator(name = "role_seq_gen",sequenceName = "role_seq")
    private Integer id;
    private String name;

    public Role(String name) {
        super();
        this.name = name;
    }
}
