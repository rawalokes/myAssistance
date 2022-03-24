package com.info.myassistant.serviceimpl;

import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.dto.TaskDto;
import com.info.myassistant.enums.TaskStatus;
import com.info.myassistant.model.Task;
import com.info.myassistant.repo.TaskRepo;
import com.info.myassistant.service.TaskService;
import com.info.myassistant.shared.BaseResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author rawalokes
 * Date:3/22/22
 * Time:3:55 PM
 */
@Service
public class TaskServiceImpl extends BaseResponse implements TaskService {

    private final TaskRepo taskRepo;

    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    //create a new task in database
    @Override
    public ResponseDto create(TaskDto taskDto) {
        try {
            taskDto.setTaskStatus(TaskStatus.pending);
            //convert task into taskDto
            Task task = new Task(taskDto);
            taskRepo.save(task);
            return successResponse("Task Added successfully", null);
        } catch (Exception e) {
            return errorResponse("Failed", null);
        }

    }

    public List<TaskDto> findAll() {
        List<Task> tasks = taskRepo.findAll();
        return tasks.stream().map(t -> new TaskDto(t)).collect(Collectors.toList());
    }

    @Override
    public ResponseDto findByID(Integer integer) {
        Optional<Task> task = taskRepo.findById(integer);
        if (task != null) {
            TaskDto taskDto = new TaskDto(task.get());
            return successResponse("", taskDto);
        } else {
            return errorResponse("Task not found", null);
        }


    }


    @Override
    public List<TaskDto> showCurrentPendingTask() {
        List<Task> tasks = taskRepo.findAllCurrentTask(TaskStatus.pending,LocalDate.now());
        return tasks.stream().map(task -> new TaskDto(task)).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> showPendingTask() {
      List<Task> pendingTask=taskRepo.findTaskByTaskStatus(TaskStatus.pending);
        return pendingTask.stream().map(task -> new TaskDto(task)).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> yesterdayTask() {
        LocalDate yesterday= LocalDate.now().minusDays(1);
        List<Task> yesterdayTask= taskRepo.findYesterdayByDate(yesterday,TaskStatus.pending);
        return yesterdayTask.stream().map(task -> new TaskDto(task)).collect(Collectors.toList());
    }

    @Override
    public ResponseDto markTaskComplete(Integer id) {
        Optional<Task> task = taskRepo.findById(id);
        if (task.isPresent()) {
            Task dataBaseTask = task.get();
            dataBaseTask.setTaskStatus(TaskStatus.completed);
            taskRepo.save(dataBaseTask);
            return successResponse("Task Marked ad completed", null);
        } else {
            return errorResponse("No such task Found", null);
        }
    }

    @Override
    public ResponseDto findByDate(LocalDate date) {
        List<Task> tasks = taskRepo.findTaskByDate(LocalDate.now());
        if (tasks != null)
            return successResponse("", tasks);
        else
            return errorResponse("No Data Found", null);

    }
}
