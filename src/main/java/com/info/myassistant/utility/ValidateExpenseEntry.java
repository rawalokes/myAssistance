package com.info.myassistant.utility;

import com.info.myassistant.dto.ExpenseDto;
import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.model.Users;
import com.info.myassistant.repo.ExpenseRepo;
import com.info.myassistant.repo.IncomeRepo;
import com.info.myassistant.shared.BaseResponse;
import org.springframework.stereotype.Component;

/**
 * @author rawalokes
 * Date:3/27/22
 * Time:1:25 PM
 */
@Component
public class ValidateExpenseEntry{
    private final IncomeRepo incomeRepo;
    private final ExpenseRepo expenseRepo;
    private final GetCurrentUserDetails currentUserDetails;

    public ValidateExpenseEntry(IncomeRepo incomeRepo
            , ExpenseRepo expenseRepo, GetCurrentUserDetails currentUserDetails) {
        this.incomeRepo = incomeRepo;
        this.expenseRepo = expenseRepo;
        this.currentUserDetails = currentUserDetails;
    }
    public Boolean validateExpense(ExpenseDto expenseDto){
        Users currentUser=currentUserDetails.getCurrentUser();
        Double currentAmount=expenseDto.getAmount();
        if (currentAmount==null){
            currentAmount=0.0;
        }
        Double totalIncome = incomeRepo.findTotalIncome(currentUser);
        Double totalExpesne = expenseRepo.findTotalExpense(currentUser)+currentAmount;

        if (totalIncome==null)
            totalIncome=0.0;
        if (totalExpesne==null)
            totalExpesne=0.0;

        if (totalIncome >= totalExpesne) {
            return true;
        }
        else {
           return false;
        }
    }
}
