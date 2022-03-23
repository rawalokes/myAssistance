package com.info.myassistant.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author rawalokes
 * Date:3/24/22
 * Time:12:21 AM
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "source")
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "source_seq_gen")
    @SequenceGenerator(name = "source_seq_gen",sequenceName = "source_seq",allocationSize = 1)
    private Integer id;

    private Double amount;
    private String discription;


}
