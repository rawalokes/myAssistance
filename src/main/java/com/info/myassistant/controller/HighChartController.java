package com.info.myassistant.controller;

import com.info.myassistant.enums.TaskStatus;
import com.info.myassistant.model.Expense;
import com.info.myassistant.model.Task;
import com.info.myassistant.model.Users;
import com.info.myassistant.repo.ExpenseRepo;
import com.info.myassistant.repo.TaskRepo;
import com.info.myassistant.utility.GetCurrentUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rawalokes
 * Date:3/28/22
 * Time:12:44 AM
 */
@Controller
public class HighChartController {
    private final TaskRepo taskRepo;
    private final ExpenseRepo expenseRepo;
    private final GetCurrentUserDetails currentUserDetails;

    public HighChartController(TaskRepo taskRepo
            , ExpenseRepo expenseRepo, GetCurrentUserDetails currentUserDetails) {
        this.taskRepo = taskRepo;
        this.expenseRepo = expenseRepo;
        this.currentUserDetails = currentUserDetails;
    }

    @GetMapping("/expense/graph")
    public String expenseChart(Model model){
        List<Expense> expense= expenseRepo.findAllExpense(currentUserDetails.getCurrentUser());
        if (expense==null){
            return "";
        }
        List<LocalDate> date= expense.stream().map(exp->exp.getDate()).collect(Collectors.toList());
        List<Double> amount= expense.stream().map(exp->exp.getAmount()).collect(Collectors.toList());
        model.addAttribute("dateList",date);
        model.addAttribute("amountList",amount);
        return "charts/expenseChart";
    }
    @GetMapping("/task/pie")
    public String taskPieGen(Model model){
        Users users=currentUserDetails.getCurrentUser();
        List<Task> task= taskRepo.findAllTaskByUser(users);
        List<Task> pendingTask=taskRepo.findTaskByTaskStatus(TaskStatus.pending,users);
        List<Task> completedTask=taskRepo.findTaskByTaskStatus(TaskStatus.completed,users);

        Integer totalTask=task.size();
        Integer totalPendingTask=pendingTask.size();
        Integer totalCompletedTask=completedTask.size();

        Integer pendingPercentage= ((totalPendingTask*100)/totalTask);
        Integer completedPercentage = ((totalCompletedTask*100)/totalTask);
        System.out.println("totalc  "+completedPercentage+" P  "+pendingPercentage);
        model.addAttribute("complete", completedPercentage);
        model.addAttribute("pending",pendingPercentage);
        return "charts/taskChart";

    }
}
