package com.info.myassistant.model;

import com.info.myassistant.dto.IncomeDto;
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
    private Integer incomeId;

    private Double amount;

    private String description;
    @ManyToOne
    @JoinColumn(name = "source_id",referencedColumnName = "sourceId",
    foreignKey = @ForeignKey(name = "fk_source_income"))
    private Source source;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_user_income"))
    private User user;


    public Income(IncomeDto incomeDto) {
        this.incomeId = incomeDto.getIncomeId();
        this.amount = incomeDto.getAmount();
        this.description=incomeDto.getDescription();

    }
}
