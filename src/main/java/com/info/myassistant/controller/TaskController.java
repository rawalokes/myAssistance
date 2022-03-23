package com.info.myassistant.controller;

import com.info.myassistant.serviceimpl.TaskServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("get-all")
    public String getAllTask(){
        return "/task/ViewTask";
    }
}
