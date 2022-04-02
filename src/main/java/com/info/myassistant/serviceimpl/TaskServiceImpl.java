package com.info.myassistant.serviceimpl;

import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.dto.TaskDto;
import com.info.myassistant.enums.TaskStatus;
import com.info.myassistant.model.Task;
import com.info.myassistant.repo.TaskRepo;
import com.info.myassistant.service.TaskService;
import com.info.myassistant.shared.BaseResponse;
import com.info.myassistant.utility.GetCurrentUserDetails;
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
    private final GetCurrentUserDetails currentUser;

    public TaskServiceImpl(TaskRepo taskRepo, GetCurrentUserDetails currentUser) {
        this.taskRepo = taskRepo;
        this.currentUser = currentUser;
    }

    //create a new task in database
    @Override
    public ResponseDto create(TaskDto taskDto) {
        try {
            taskDto.setTaskStatus(TaskStatus.pending);
            //convert task into taskDto
            Task task = new Task(taskDto);
            task.setUsers(currentUser.getCurrentUser());
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
        List<Task> tasks = taskRepo.findAllTaskByStatus(TaskStatus.pending, LocalDate.now(), currentUser.getCurrentUser());
        return tasks.stream().map(task -> new TaskDto(task)).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> showPendingTask() {
        List<Task> pendingTask = taskRepo.findTaskByTaskStatus(TaskStatus.pending, currentUser.getCurrentUser());
        return pendingTask.stream().map(task -> new TaskDto(task)).collect(Collectors.toList());
    }


    @Override
    public List<TaskDto> yesterdayTask() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        List<Task> yesterdayTask = taskRepo.findAllTaskByStatus(TaskStatus.pending, yesterday, currentUser.getCurrentUser());
        return yesterdayTask.stream().map(task -> new TaskDto(task)).collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> showCompleteTask() {
        try {
            //show completed task only
            List<Task> task=taskRepo.findTaskByTaskStatus
                    (TaskStatus.completed,currentUser.getCurrentUser());
            List<TaskDto> taskDtos=task.stream().map(currentTask ->new TaskDto(currentTask)).collect(Collectors.toList());
            return taskDtos;
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public ResponseDto addOldTaskToTodayTask(Integer id) {
        Optional<Task> task = taskRepo.findById(id);
        if (task.isPresent()) {
            //retrieve task from database
            Task databaseTask = task.get();
            //set date as current date
            databaseTask.setDate(LocalDate.now());
            //save updated task to database
            taskRepo.save(databaseTask);
            return successResponse(" Task added successfully",null);
        }
        return errorResponse("Task not found",null);
    }


    @Override
    public ResponseDto markTaskComplete(Integer id) {
        //find task by id
        Optional<Task> task = taskRepo.findById(id);
        if (task.isPresent()) {
            //get task
            Task dataBaseTask = task.get();
            //set task status as completed
            dataBaseTask.setTaskStatus(TaskStatus.completed);
            //update task
            taskRepo.save(dataBaseTask);
            //return success response
            return successResponse("Task Marked as completed", null);
        } else {
            return errorResponse("No such task Found", null);
        }
    }

    @Override
    public ResponseDto findByDate() {
        //find task by date
        List<Task> tasks = taskRepo.findTaskByDate(LocalDate.now(), currentUser.getCurrentUser());
        if (tasks != null)

            return successResponse("", tasks);
        else
            return errorResponse("No Data Found", null);

    }
}
