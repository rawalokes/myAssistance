package com.info.myassistant.serviceimpl;

import com.info.myassistant.dto.IncomeDto;
import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.model.Income;
import com.info.myassistant.repo.IncomeRepo;
import com.info.myassistant.repo.SourceRepo;
import com.info.myassistant.service.IncomeService;
import com.info.myassistant.shared.BaseResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rawalokes
 * Date:3/24/22
 * Time:1:33 PM
 */
@Service
public class IncomeServiceImpl extends BaseResponse implements IncomeService {
    private final IncomeRepo incomeRepo;
    private final SourceRepo sourceRepo;

    public IncomeServiceImpl(IncomeRepo incomeRepo, SourceRepo sourceRepo) {
        this.incomeRepo = incomeRepo;
        this.sourceRepo = sourceRepo;
    }

    @Override
    public ResponseDto create(IncomeDto incomeDto) {
        Income income =new Income(incomeDto);
        incomeRepo.save(income);
        return successResponse("Income Saved Successfully",null);
    }

    @Override
    public ResponseDto findByID(Integer integer) {
        return null;
    }

    @Override
    public List<IncomeDto> findAll() {

        return null;
    }
}
