package com.info.myassistant.controller.task;

import com.info.myassistant.serviceimpl.task.TaskServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rawalokes
 * Date:3/22/22
 * Time:3:57 PM
 */
@RequestMapping("/task")
public class TaskController {
    private final TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }
}
