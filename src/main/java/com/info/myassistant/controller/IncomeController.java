package com.info.myassistant.controller;

import com.info.myassistant.dto.IncomeDto;
import com.info.myassistant.serviceimpl.IncomeServiceImpl;
import com.info.myassistant.serviceimpl.SourceServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rawalokes
 * Date:3/24/22
 * Time:1:58 PM
 */
@Controller
@RequestMapping("/income")
public class IncomeController {
    private final IncomeServiceImpl incomeService;
    private final SourceServiceImpl sourceService;

    public IncomeController(IncomeServiceImpl incomeService, SourceServiceImpl sourceService) {
        this.incomeService = incomeService;
        this.sourceService = sourceService;
    }
    @GetMapping("/get-all")
    public String getAll(){
       return "";
    }
    @GetMapping("/create")
    public String getCreateIncome(Model model){
        model.addAttribute("incomeDetails",new IncomeDto());
        model.addAttribute("sourcesDetails",sourceService.findAllSource());
      return "income/createIncome";
    }
}
