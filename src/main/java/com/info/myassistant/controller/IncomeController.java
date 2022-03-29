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

    /**
     *
     * @param model
     * @return view of all income pages
     */
    @GetMapping("/get-all")
    public String getAll(Model model){
        // list of current user income
        model.addAttribute("incomeList",incomeService.findAll());
        // total income minus total expense
        model.addAttribute("totalIncome",incomeService.findTotalRemainingIncome());
       return "income/viewIncome";
    }

    /**
     *
     * @param model
     * @return create income form page
     */
    @GetMapping("/create")
    public String getCreateIncome(Model model){
        model.addAttribute("incomeDetails",new IncomeDto());
        //list if source for current user
        model.addAttribute("sourcesDetails",sourceService.findAllSource());
      return "income/createIncome";
    }

    /**
     *
     * @param bindingResult for error handeling
     * @param model
     * @return view all income page
     */
    @PostMapping("/create")
    public String postCreateIncome(@Valid @ModelAttribute("incomeDetails") IncomeDto incomeDto
            , BindingResult bindingResult,Model model){
        //check if there is any binding error if present return create income page with error
        if (bindingResult.hasErrors()){
            //list if source for current user
            model.addAttribute("sourcesDetails",sourceService.findAllSource());
            return "income/createIncome";
        }
        //call service to create income
        ResponseDto responseDto= incomeService.create(incomeDto);
        //check responseDto status is true and return list of income page
        if(responseDto.isStatus()) {
           return "redirect:/income/get-all";
        }
        //if responseDto status is false return create income form with error message
        model.addAttribute("errorMessage", responseDto.getMessage());
        return "income/createIncome";
    }
}
