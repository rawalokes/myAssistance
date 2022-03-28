package com.info.myassistant.controller;

import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.dto.TaskDto;

import com.info.myassistant.serviceimpl.TaskServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**
 * @author rawalokes
 * Date:3/22/22
 * Time:3:57 PM
 */
@Controller
@RequestMapping("/task")
public class TaskController {
    private final TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/get-all")
    public String getCurrentPendingTask(Model model) {
        model.addAttribute("taskList", taskService.showCurrentPendingTask());
        return "task/viewTask";
    }

    @GetMapping("/pending")
    public String getAllPendingTask(Model model) {
        model.addAttribute("pendingTaskList", taskService.yesterdayTask());
        return "task/pendingTask";
    }

    @GetMapping("create")
    public String getCreateTask(Model model) {
        model.addAttribute("taskDetails", new TaskDto());
        return "task/createTask";

    }

    @PostMapping("/create")
    public String postCreateTask(@Valid @ModelAttribute("taskDetails") TaskDto taskDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "task/createTask";
        }
        ResponseDto responseDto = taskService.create(taskDto);
        if (responseDto.isStatus()) {
            return "redirect:/task/get-all";
        } else {
            model.addAttribute("errorMessage", responseDto.getMessage());
            return "task/createTask";
        }

    }

    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Integer id, Model model) {
        ResponseDto responseDto = taskService.findByID(id);
        if (responseDto.isStatus()) {
            model.addAttribute("taskDetails", responseDto.getData());
            return "task/createTask";
        } else {
            model.addAttribute("errorMessage", responseDto.getMessage());
            return "task/viewTask";
        }

    }

    @GetMapping("/complete/{id}")
    public String markTaskComplete(@PathVariable Integer id, Model model) {
        ResponseDto responseDto = taskService.markTaskComplete(id);
        if (responseDto.isStatus()) {
            return "redirect:/task/get-all";
        }
        model.addAttribute("errorMessage", responseDto.getMessage());
        return "task/viewTask";
    }




}
