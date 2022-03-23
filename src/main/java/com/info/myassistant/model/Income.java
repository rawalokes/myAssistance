package com.info.myassistant.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author rawalokes
 * Date:3/24/22
 * Time:12:17 AM
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "income_seq_gen")
    @SequenceGenerator(name = "income_seq_gen",sequenceName = "income_seq",allocationSize = 1)
    @Column(name = "income_Id")
    private Integer incomeId;

    private Double amount;

    private String discription;
    @ManyToOne
    @JoinColumn(name = "source_id",referencedColumnName = "income_Id",
    foreignKey = @ForeignKey(name = "fk_income_source"))
    private Source source;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "income_Id",
            foreignKey = @ForeignKey(name = "fk_income_user"))
    private Income income;
}