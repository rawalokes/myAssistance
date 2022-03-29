package com.info.myassistant.serviceimpl;

import com.info.myassistant.dto.ExpenseDto;
import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.model.Expense;
import com.info.myassistant.model.Users;
import com.info.myassistant.repo.ExpenseRepo;
import com.info.myassistant.service.ExpenseService;
import com.info.myassistant.shared.BaseResponse;
import com.info.myassistant.utility.GetCurrentUserDetails;
import com.info.myassistant.utility.ValidateExpenseEntry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author rawalokes
 * Date:3/24/22
 * Time:10:57 PM
 */
@Service
public class ExpenseServiceImpl extends BaseResponse implements ExpenseService {
    private final ExpenseRepo expenseRepo;

    private final GetCurrentUserDetails currentUserDetails;
    private final ValidateExpenseEntry validateExpenseEntry;

    public ExpenseServiceImpl(ExpenseRepo expenseRepo
            , GetCurrentUserDetails currentUserDetails
            , ValidateExpenseEntry validateExpenseEntry) {
        this.expenseRepo = expenseRepo;
        this.currentUserDetails = currentUserDetails;
        this.validateExpenseEntry = validateExpenseEntry;
    }

    @Override
    public ResponseDto create(ExpenseDto expenseDto) {
        try {
            //get current user
            Users currentUser = currentUserDetails.getCurrentUser();
            //convert expenseDto into expense
            Expense expense = new Expense(expenseDto);
            //set user as current user in expense
            expense.setUsers(currentUser);
            //use ValidExpenseEntry class to validate expense
            if (validateExpenseEntry.validateExpense(expenseDto)) {
                //store expense in database
                expenseRepo.save(expense);
                return successResponse("Expense added successfully", null);

            } else {
                return errorResponse("Expense exceed income", null);
            }

        } catch (Exception e) {
           errorResponse(e.getMessage(),null);

        }

        return null;
    }

    @Override
    public ResponseDto findByID(Integer integer) {
        Optional<Expense> expense = expenseRepo.findById(integer);
        if (expense.isPresent()) {
            return successResponse("", new ExpenseDto(expense.get()));
        }
        return errorResponse("Expense not found", null);
    }

    @Override
    public List<ExpenseDto> findAllExpense() {
        List<Expense> expenses = expenseRepo.findAllExpense(currentUserDetails.getCurrentUser());
        return expenses.stream().map(expense -> new ExpenseDto(expense)).collect(Collectors.toList());
    }

    /**
     *
     * @return total expense of current user
     */
    public Double findTotalExpense() {
        return expenseRepo.findTotalExpense(currentUserDetails.getCurrentUser());
    }
}
