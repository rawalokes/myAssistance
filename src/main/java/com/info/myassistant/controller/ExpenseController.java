package com.info.myassistant.controller;

import com.info.myassistant.dto.ExpenseDto;
import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.serviceimpl.ExpenseServiceImpl;
import com.info.myassistant.serviceimpl.IncomeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author rawalokes
 * Date:3/24/22
 * Time:11:13 PM
 */
@Controller
@RequestMapping("/expense")
public class ExpenseController {
    private final ExpenseServiceImpl expenseService;
    private final IncomeServiceImpl incomeService;

    public ExpenseController(ExpenseServiceImpl expenseService
            , IncomeServiceImpl incomeService) {
        this.expenseService = expenseService;
        this.incomeService = incomeService;
    }

    /**
     *
     * @param model
     * @return
     */
    @GetMapping("/get-all")
    public String getAll(Model model) {
        model.addAttribute("expenseList", expenseService.findAllExpense());
        model.addAttribute("totalExpense", expenseService.findTotalExpense());
        model.addAttribute("remainingIncome", incomeService.findTotalRemainingIncome());
        return "expense/viewExpense";
    }

    /**
     * get mapping for create expense page
     * @param model
     * @return
     */
    @GetMapping("/create")
    public String getCreateExpense(Model model) {
        model.addAttribute("expenseDetails", new ExpenseDto());
        return "expense/createExpense";
    }

    /**
     * post mapping for create rent page
     * @param expenseDto
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/create")
    public String postCreateExpense(@Valid @ModelAttribute("expenseDetails") ExpenseDto expenseDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "expense/createExpense";
        }
        ResponseDto responseDto = expenseService.create(expenseDto);
        if (responseDto.isStatus()) {
            return "redirect:/expense/get-all";
        }
        model.addAttribute("errorMessage", responseDto.getMessage());
        return "expense/createExpense";
    }

    @GetMapping("/edit/{id}")
    public String editExpense(@PathVariable("id") Integer integer, Model model) {
        ResponseDto responseDto = expenseService.findByID(integer);
        if (responseDto.isStatus()) {
            model.addAttribute("expenseDetails", responseDto.getData());
            return "redirect:/expense/get-all";
        }
        model.addAttribute("errorMessage", responseDto.getMessage());
        return "expense/createExpense";
    }

}
