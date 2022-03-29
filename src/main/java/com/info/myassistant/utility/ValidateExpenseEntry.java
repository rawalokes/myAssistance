package com.info.myassistant.utility;

import com.info.myassistant.dto.ExpenseDto;
import com.info.myassistant.model.Users;
import com.info.myassistant.repo.ExpenseRepo;
import com.info.myassistant.repo.IncomeRepo;
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

    /**
     *  validate expense by checking if income is greater than expense
     * @param expenseDto
     * @return
     */
    public Boolean validateExpense(ExpenseDto expenseDto){
        //get current user
        Users currentUser=currentUserDetails.getCurrentUser();
        //get current expense amount user wish to save
        Double currentAmount=expenseDto.getAmount();
        //get user's total income
        Double totalIncome = incomeRepo.findTotalIncome(currentUser);
        //if null set value to zero to avoid NullPointException and show total income as zero
        if (totalIncome==null)
            totalIncome=0.0;
        //get total user's expense and set total expense to zero if it return null
        Double totalExpense = expenseRepo.findTotalExpense(currentUser);
        if (totalExpense==null)
            totalExpense=0.0;
        //total expense in database plus current amount expense amount entered by user
        Double afterCurrentExpenseTotal =totalExpense+currentAmount;
        /**
         * return true if total expense is less than or equals to total expense else false
         */
        if (totalIncome >= afterCurrentExpenseTotal) {
            return true;
        }
        else {
           return false;
        }
    }
}
