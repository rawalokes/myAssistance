package com.info.myassistant.controller;

import com.info.myassistant.dto.ExpenseDto;
import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.serviceimpl.ExpenseServiceImpl;
import org.springframework.boot.Banner;
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
 * Time:11:13 PM
 */
@Controller
@RequestMapping("/expense")
public class ExpenseController {
    private final ExpenseServiceImpl expenseService;
    public ExpenseController(ExpenseServiceImpl expenseService) {
        this.expenseService = expenseService;
    }
    @GetMapping("/get-all")
    public  String getAll(Model model){
        model.addAttribute("expenseList",expenseService.findAllExpense());
        return "expense/viewExpense";
    }

    @GetMapping("/create")
    public String getCreateExpense(Model model){
        model.addAttribute("expenseDetails",new ExpenseDto());
        return "expense/createExpense";
    }

    @PostMapping("/create")
    public String postCreateExpense(@Valid @ModelAttribute("expenseDetails")ExpenseDto expenseDto
            , BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            return "expense/createExpense";
        }
        ResponseDto responseDto= expenseService.create(expenseDto);
        return "redirect:/expense/get-all";
    }
}
