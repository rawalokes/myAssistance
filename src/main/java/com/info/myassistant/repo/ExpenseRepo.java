package com.info.myassistant.repo;

import com.info.myassistant.model.Expense;
import com.info.myassistant.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense,Integer> {
    @Query("SELECT e FROM Expense e WHERE   e.users=?1")
    List<Expense> findAllExpense(Users users);
}
