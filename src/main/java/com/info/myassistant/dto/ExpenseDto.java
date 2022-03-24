package com.info.myassistant.dto;

import lombok.*;

import javax.validation.constraints.*;

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
    @NotEmpty(message = "Expense type cannot be null")
    private String expenseType;

    private String description;

}
