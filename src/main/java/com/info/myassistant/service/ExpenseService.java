package com.info.myassistant.service;

import com.info.myassistant.dto.ExpenseDto;
import com.info.myassistant.shared.GenericService;

import java.util.List;

public interface ExpenseService extends GenericService<ExpenseDto,Integer> {
    List<ExpenseDto> findAllExpense();
}
