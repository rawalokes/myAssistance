package com.info.myassistant.model;

import com.info.myassistant.dto.TaskDto;
import com.info.myassistant.enums.TaskStatus;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sql_gen")
    @SequenceGenerator(name = "task_seq_gen", sequenceName = "task_seq", allocationSize = 1)
    private Integer taskId;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus taskStatus;

    private LocalDate date;

    private String remarks;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_user_task"))
    private Users users;


    /**
     * convert TaskDto into Task
     *
     * @param taskDto
     */
    public Task(TaskDto taskDto) {
        this.taskId = taskDto.getTaskId();
        this.name = taskDto.getName();
        this.date =LocalDate.now().minusDays(1);
        this.taskStatus=taskDto.getTaskStatus();
        this.remarks = taskDto.getRemarks();
    }
}
