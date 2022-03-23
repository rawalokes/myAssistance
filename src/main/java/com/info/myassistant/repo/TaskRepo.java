package com.info.myassistant.repo;

import com.info.myassistant.enums.TaskStatus;
import com.info.myassistant.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task,Integer> {
    List<Task> findTaskByTaskStatus(TaskStatus taskStatus);
    List<Task> findTaskByDate(LocalDate date);

    @Query("SELECT t FROM Task t WHERE t.taskStatus = ?1 and t.date = ?2")
    List<Task> findAllCurrentTask(TaskStatus taskStatus,LocalDate date);

    @Query("SELECT t FROM Task t WHERE t.date = ?1 and t.taskStatus = ?2 ORDER BY t.date desc ")
    List<Task> findYesterdayByDate(LocalDate date,TaskStatus taskStatus);

}