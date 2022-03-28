package com.info.myassistant.model;

import com.info.myassistant.dto.ExpenseDto;
import com.info.myassistant.enums.ExpenseType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author rawalokes
 * Date:3/24/22
 * Time:10:45 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "expense_seq_gen")
    @SequenceGenerator(name = "expense_seq_gen",sequenceName = "expense_seq",allocationSize = 1)
    private Integer id;
    private String name;
    private Double amount;
    private LocalDate date;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_user_expense"))
    private Users users;

    public Expense(ExpenseDto expenseDto) {
        this.id = expenseDto.getId();
        this.name = expenseDto.getName();
        this.amount = expenseDto.getAmount();
        this.description = expenseDto.getDescription();
        this.date=LocalDate.now();
    }
}
