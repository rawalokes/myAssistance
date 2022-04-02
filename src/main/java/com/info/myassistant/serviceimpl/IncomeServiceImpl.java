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
            //convert income dto to income
            Income income = converterIncomeDtoToIncome(incomeDto);
            //set current user as foreign key on income table
            income.setUsers(currentUserDetails.getCurrentUser());
            //create income
            incomeRepo.save(income);
            return successResponse("Income Saved Successfully", null);
        }catch (NumberFormatException e){
            return errorResponse("Please enter numbers",null);
        }

    }

    @Override
    public ResponseDto findByID(Integer integer) {
        //find income by id
        Optional<Income> income= incomeRepo.findById(integer);
        if (income.isPresent()){
            return successResponse("",income);
        }
        return errorResponse("Income not found",null);
    }

    @Override
    public List<IncomeDto> findAll() {
        //find all income for current user
        List<Income> incomes = incomeRepo.findAllIncome(currentUserDetails.getCurrentUser());
        //return list of income dto
        //convert income into income dto
        return incomes.stream().map(income -> converterIncomeToIncomeDto(income) )
                .collect(Collectors.toList());
    }
    public Double findTotalRemainingIncome(){
        //find total income amount
        Double income=incomeRepo.findTotalIncome(currentUserDetails.getCurrentUser());
        //if total amount is null return 0 to avoid NullPointException
        if (income==null){
            return 0.0;
        }
        //find total expense amount
        Double expense=expenseRepo.findTotalExpense(currentUserDetails.getCurrentUser());
        //if total amount is null return 0 to avoid NullPointException
        if (expense==null){
            return income;
        }
        //get remaining balance by subtracting expense from  income
        Double remainingBalance =income-expense;
        return remainingBalance;

    }

    /**
     *convert income into income dto
     *
     * @param income to be converted into dto
     * @return income dto
     */
    private IncomeDto converterIncomeToIncomeDto(Income income) {
        return IncomeDto.builder()
                .incomeId(income.getIncomeId())
                .amount(income.getAmount())
                .description(income.getDescription())
                //find source name
                .sourceName(sourceRepo.findById(income
                                .getSource().getSourceId())
                        .get().getSourceName())
                //get total income user have
                .totalIncome(incomeRepo.findTotalIncome(currentUserDetails.getCurrentUser()))
                .build();
    }

    /**
     * convert incomeDto into income
     *
     * @param incomeDto to be converted into income
     * @return income
     */
    private Income converterIncomeDtoToIncome(IncomeDto incomeDto) {
        return Income.builder()
                .incomeId(incomeDto.getIncomeId())
                .amount(incomeDto.getAmount())
                .description(incomeDto.getDescription())
                //find source by source id
                .source(sourceRepo.findById(incomeDto.getSourceId()).get())

                .build();
    }
}
