package com.info.myassistant.dto;

import com.info.myassistant.enums.ExpenseType;
import com.info.myassistant.model.Expense;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * @author rawalokes
 * Date:3/24/22
 * Time:10:47 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExpenseDto {
    private Integer id;
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, message = "Minimum three character required")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+",message = "Name cannot have special character number or whitespace")
    private String name;

    @NotNull(message = "Amount cannot be Empty")
    @Min(value = 1,message = "Amount cannot be less than zero")

    private Double amount;


    private Double totalExpense;

    private LocalDate date;


    private Integer userId;

    private String description;
    public ExpenseDto(Expense expense) {
        this.id = expense.getId();
        this.name = expense.getName();
        this.amount = expense.getAmount();

        this.description = expense.getDescription();
        this.date=expense.getDate();

    }

}
