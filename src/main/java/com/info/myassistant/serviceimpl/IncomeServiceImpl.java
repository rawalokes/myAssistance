package com.info.myassistant.serviceimpl;

import com.info.myassistant.dto.IncomeDto;
import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.model.Income;
import com.info.myassistant.repo.ExpenseRepo;
import com.info.myassistant.repo.IncomeRepo;
import com.info.myassistant.repo.SourceRepo;
import com.info.myassistant.service.IncomeService;
import com.info.myassistant.shared.BaseResponse;
import com.info.myassistant.utility.GetCurrentUserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author rawalokes
 * Date:3/24/22
 * Time:1:33 PM
 */
@Service
public class IncomeServiceImpl extends BaseResponse implements IncomeService {
    private final IncomeRepo incomeRepo;
    private final SourceRepo sourceRepo;
    private final ExpenseRepo expenseRepo;
    private final GetCurrentUserDetails currentUserDetails;

    public IncomeServiceImpl(IncomeRepo incomeRepo
            , SourceRepo sourceRepo
            , ExpenseRepo expenseRepo
            , GetCurrentUserDetails currentUserDetails) {
        this.incomeRepo = incomeRepo;
        this.sourceRepo = sourceRepo;
        this.expenseRepo = expenseRepo;
        this.currentUserDetails = currentUserDetails;
    }

    @Override
    public ResponseDto create(IncomeDto incomeDto) {
        try {
            Income income = converterIncomeDtoToIncome(incomeDto);
            income.setUsers(currentUserDetails.getCurrentUser());
            incomeRepo.save(income);
            return successResponse("Income Saved Successfully", null);
        }catch (NumberFormatException e){
            System.out.println(e);
            return errorResponse("Please enter numbers",null);
        }

    }

    @Override
    public ResponseDto findByID(Integer integer) {
        Optional<Income> income= incomeRepo.findById(integer);
        if (income.isPresent()){
            return successResponse("",income);
        }
        return errorResponse("Income not found",null);
    }

    @Override
    public List<IncomeDto> findAll() {
        List<Income> incomes = incomeRepo.findAllIncome(currentUserDetails.getCurrentUser());
        return incomes.stream().map(income -> converterIncomeToIncomeDto(income) )
                .collect(Collectors.toList());
    }
    public Double findTotalRemainingIncome(){
        Double income=incomeRepo.findTotalIncome(currentUserDetails.getCurrentUser());
        if (income==null){
            return 0.0;
        }
        Double expense=expenseRepo.findTotalExpense(currentUserDetails.getCurrentUser());
        if (expense==null){
            return income;
        }
        Double remainingBalance =income-expense;
        return remainingBalance;

    }

    private IncomeDto converterIncomeToIncomeDto(Income income) {
        return IncomeDto.builder()
                .incomeId(income.getIncomeId())
                .amount(income.getAmount())
                .description(income.getDescription())
                .sourceName(sourceRepo.findById(income
                                .getSource().getSourceId())
                        .get().getSourceName())
                .totalIncome(incomeRepo.findTotalIncome(currentUserDetails.getCurrentUser()))
                .build();
    }
    private Income converterIncomeDtoToIncome(IncomeDto incomeDto) {
        return Income.builder()
                .incomeId(incomeDto.getIncomeId())
                .amount(incomeDto.getAmount())
                .description(incomeDto.getDescription())
                .source(sourceRepo.findById(incomeDto.getSourceId()).get())

                .build();
    }
}
