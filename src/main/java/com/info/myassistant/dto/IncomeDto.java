package com.info.myassistant.dto;

import com.info.myassistant.model.Income;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author rawalokes
 * Date:3/24/22
 * Time:12:27 AM
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class IncomeDto {

    private Integer incomeId;

    @NotNull(message = "Amount cannot be Empty")
    @Min(value = 1,message = "Amount cannot be less than zero")
    @Pattern(regexp ="[0-9]+",message = "Only numbers are allowed")
    private Double amount;

    @NotEmpty(message = "Description cannot be Empty")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+",message = "Description cannot be Empty")
    private String description;
    @NotNull(message = "Please select a source")
    private Integer sourceId;

    private String sourceName;

    public IncomeDto(Income income) {
        this.incomeId = income.getIncomeId();
        this.amount = income.getAmount();
        this.description = income.getDescription();
        this.sourceId = income.getSource().getSourceId();
    }
}
