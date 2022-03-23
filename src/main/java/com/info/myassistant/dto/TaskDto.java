package com.info.myassistant.dto;

import com.info.myassistant.model.Task;
import jdk.jfr.Timestamp;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rawalokes
 * Date:3/21/22
 * Time:6:29 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TaskDto {

    private Integer taskId;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3,message = "Invalid name")
    @Pattern(regexp = "[a-zA-Z]+",message = "Name cannot have special character or number")
    private String name;

    @NotEmpty(message = "Starting time cannot be empty")
    private String startTime;

    @NotEmpty(message = "Deadline cannot be empty")

    private String deadline;

    private LocalDate date;

    @Size(min = 5,message = "Minimum five character required")
    private String remarks;

    private List<Integer> userId;

    /**
     * convert Task into Task Dto
     * @param task
     */
    public TaskDto(Task task){
        this.taskId=task.getTaskId();
        this.name=task.getName();
        this.date=task.getDate();
        this.startTime=task.getStartTime();
        this.deadline=task.getDeadline();
        this.remarks=task.getRemarks();
        this.userId= task.getUser().stream().map(u->u.getUserId()).collect(Collectors.toList());
    }

}
