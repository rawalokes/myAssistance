package com.info.myassistant.controller;

import com.info.myassistant.dto.IncomeDto;
import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.serviceimpl.IncomeServiceImpl;
import com.info.myassistant.serviceimpl.SourceServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
    public String getAll(Model model){
        model.addAttribute("incomeList",incomeService.findAll());
        model.addAttribute("totalIncome",incomeService.findTotalRemainingIncome());
       return "income/viewIncome";
    }
    @GetMapping("/create")
    public String getCreateIncome(Model model){
        model.addAttribute("incomeDetails",new IncomeDto());
        model.addAttribute("sourcesDetails",sourceService.findAllSource());
      return "income/createIncome";
    }
    @PostMapping("/create")
    public String postCreateIncome(@Valid @ModelAttribute("incomeDetails") IncomeDto incomeDto
            , BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("sourcesDetails",sourceService.findAllSource());
            return "income/createIncome";
        }
        ResponseDto responseDto= incomeService.create(incomeDto);
        if(responseDto.isStatus()) {
           return "redirect:/income/get-all";
        }
        model.addAttribute("errorMessage", responseDto.getMessage());
        return "income/createIncome";
    }
}
