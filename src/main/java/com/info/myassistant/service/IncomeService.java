package com.info.myassistant.service;

import com.info.myassistant.dto.IncomeDto;
import com.info.myassistant.shared.GenericService;

import java.util.List;

public interface IncomeService extends GenericService<IncomeDto,Integer> {
    List<IncomeDto> findAll();
}
