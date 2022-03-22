package com.info.myassistant.dto.taskDto;

import com.info.myassistant.model.task.Task;
import lombok.*;

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

    private String name;

    private String startTime;

    private String deadline;

    private LocalDate date;

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
