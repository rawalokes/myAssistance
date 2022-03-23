package com.info.myassistant.controller;

import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.dto.TaskDto;
import com.info.myassistant.serviceimpl.TaskServiceImpl;
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
    public String getAllTask(Model model){

        model.addAttribute("taskList", taskService.findAll());
        return "task/viewTask";
    }

    @GetMapping("create")
    public String getCreateTask(Model model){
        model.addAttribute("taskDetails",new TaskDto());
        return "task/createTask";

    }
    @PostMapping("/create")
    public String postCreateTask(@Valid @ModelAttribute("taskDetails") TaskDto taskDto,
                                 BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            return "task/createTask";
        }
       taskService.create(taskDto);
        return "task/createTask";
    }
}
