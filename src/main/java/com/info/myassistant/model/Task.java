package com.info.myassistant.model;

import com.info.myassistant.dto.TaskDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author rawalokes
 * Date:3/21/22
 * Time:6:22 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "task_sql_gen")
    @SequenceGenerator(name = "task_seq_gen" ,sequenceName = "task_seq",allocationSize =1)
    private Integer taskId;

    private String name;

    private String startTime;

    private String deadline;

    private LocalDate date;

    private String remarks;


    /**
     *  convert TaskDto into Task
     * @param taskDto
     */
    public Task(TaskDto taskDto) {
        this.taskId = taskDto.getTaskId();
        this.name = taskDto.getName();
        this.startTime=taskDto.getStartTime();
        this.deadline = taskDto.getDeadline();
        this.date=taskDto.getDate();
        this.remarks = taskDto.getRemarks();
    }
}
