package com.info.myassistant.repo;

import com.info.myassistant.enums.TaskStatus;
import com.info.myassistant.model.Task;
import com.info.myassistant.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task,Integer> {
    //find task by user
    @Query("SELECT t FROM Task t where t.users=?1" )
    List<Task> findAllTaskByUser(Users task);
    //find task by user and task status
    @Query("SELECT t FROM Task t WHERE  t.taskStatus = ?1 and t.users=?2")
    List<Task> findTaskByTaskStatus(TaskStatus taskStatus ,Users users);
    //find task by user and date
    @Query("SELECT t FROM Task t WHERE  t.date = ?1 and t.users=?2")
    List<Task> findTaskByDate(LocalDate date,Users users);
    //find task by status date and user
    @Query("SELECT t FROM Task t WHERE t.taskStatus = ?1 and t.date = ?2 and t.users=?3")
    List<Task> findAllTaskByStatus(TaskStatus taskStatus, LocalDate date, Users users);
//
//    @Query("SELECT t FROM Task t WHERE t.date = ?1 and t.taskStatus = ?2 and t.users=?3 ORDER BY t.date desc ")
//    List<Task> findYesterdayByDate(LocalDate date,TaskStatus taskStatus,Users users);


}
