package com.info.myassistant.dto;

import com.info.myassistant.enums.TaskStatus;
import com.info.myassistant.model.Task;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

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
    @Size(min = 3, message = "Minimum three character required")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+",message = "Name cannot have" +
            " special character number or whitespace")
    private String name;

    private TaskStatus taskStatus;

    private LocalDate date;

    @Size(min = 5, message = "Minimum five character required")
    private String remarks;

    private List<Integer> userId;

    /**
     * convert Task into Task Dto
     *
     * @param task
     */
    public TaskDto(Task task) {
        this.taskId = task.getTaskId();
        this.name = task.getName();
        this.date = task.getDate();
        this.taskStatus=task.getTaskStatus();
        this.remarks = task.getRemarks();

    }

}
