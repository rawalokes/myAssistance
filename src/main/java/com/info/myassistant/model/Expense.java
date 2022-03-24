package com.info.myassistant.model;

import com.info.myassistant.enums.ExpenseType;
import lombok.*;

import javax.persistence.*;

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
    @Enumerated(value = EnumType.STRING)
    private ExpenseType expenseType;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_user_expense"))
    private User user;
}
