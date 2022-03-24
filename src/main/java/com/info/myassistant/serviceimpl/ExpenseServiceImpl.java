package com.info.myassistant.serviceimpl;

import com.info.myassistant.dto.ExpenseDto;
import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.model.Expense;
import com.info.myassistant.repo.ExpenseRepo;
import com.info.myassistant.service.ExpenseService;
import com.info.myassistant.shared.BaseResponse;
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

    public ExpenseServiceImpl(ExpenseRepo expenseRepo) {
        this.expenseRepo = expenseRepo;
    }

    @Override
    public ResponseDto create(ExpenseDto expenseDto) {
        Expense expense= new Expense(expenseDto);
       return successResponse("Expense added successfully",null);
    }

    @Override
    public ResponseDto findByID(Integer integer) {
        Optional<Expense> expense=expenseRepo.findById(integer);
        if (expense.isPresent()){
            return successResponse("",new ExpenseDto(expense.get()));
        }
        return errorResponse("Expense not found",null);
    }

    @Override
    public List<ExpenseDto> findAllExpense() {
        List<Expense> expenses= expenseRepo.findAll();
        return expenses.stream().map(expense -> new ExpenseDto(expense)).collect(Collectors.toList());
    }
}