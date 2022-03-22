package com.info.myassistant.repo.task;

import com.info.myassistant.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task,Integer> {
}
