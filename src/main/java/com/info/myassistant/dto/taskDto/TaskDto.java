package com.info.myassistant.dto.taskDto;

import com.info.myassistant.model.task.Task;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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
    }

}
