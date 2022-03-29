package com.info.myassistant.controller;

import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.dto.TaskDto;
import com.info.myassistant.serviceimpl.TaskServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    /**
     * @param model
     * @return
     */
    @GetMapping("/get-all")
    public String getCurrentPendingTask(Model model) {
        //list of today's pending task
        model.addAttribute("taskList", taskService.showCurrentPendingTask());
        return "task/viewTask";
    }

    /**
     * @param model
     * @return
     */
    @GetMapping("/pending")
    public String getAllPendingTask(Model model) {
        //list of yesterday's pending task
        model.addAttribute("pendingTaskList", taskService.yesterdayTask());
        return "task/pendingTask";
    }

    /**
     * return create task page
     *
     * @param model
     * @return
     */
    @GetMapping("create")
    public String getCreateTask(Model model) {
        model.addAttribute("taskDetails", new TaskDto());
        return "task/createTask";

    }

    /**
     * @param taskDto
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/create")
    public String postCreateTask(@Valid @ModelAttribute("taskDetails") TaskDto taskDto, BindingResult bindingResult, Model model) {
        //check if there is any binding error  present return create task page with errors
        if (bindingResult.hasErrors()) {
            return "task/createTask";
        }
        //call service to create task
        ResponseDto responseDto = taskService.create(taskDto);
        //check responseDto status is true and redirect to view all sources
        if (responseDto.isStatus()) {
            return "redirect:/task/get-all";
            //if responseDto status is false return create task form with error message
        } else {
            model.addAttribute("errorMessage", responseDto.getMessage());
            return "task/createTask";
        }

    }

    /**
     * @param id    task id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Integer id, Model model) {
        //call service to find task by id
        ResponseDto responseDto = taskService.findByID(id);
        //check responseDto status is true and redirect to view all tasks
        if (responseDto.isStatus()) {
            model.addAttribute("taskDetails", responseDto.getData());
            return "task/createTask";
            //if responseDto status is false return create task form with error message
        } else {
            model.addAttribute("errorMessage", responseDto.getMessage());
            return "task/viewTask";
        }

    }

    /**
     * Mark task as complete and hide from pending task view
     *
     * @param id    task id
     * @param model
     * @return
     */
    @GetMapping("/complete/{id}")
    public String markTaskComplete(@PathVariable Integer id, Model model) {
        //call service to find and marks as complete
        ResponseDto responseDto = taskService.markTaskComplete(id);
        //check responseDto status is true and redirect to view all tasks
        if (responseDto.isStatus()) {
            return "redirect:/task/get-all";
        }
        //if responseDto status is false return create task form with error message
        model.addAttribute("errorMessage", responseDto.getMessage());
        return "task/viewTask";
    }
}
