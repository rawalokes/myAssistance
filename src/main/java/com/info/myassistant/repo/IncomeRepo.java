package com.info.myassistant.repo;

import com.info.myassistant.model.Income;
import com.info.myassistant.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepo extends JpaRepository<Income,Integer> {
    @Query("SELECT i FROM Income i WHERE   i.users=?1")
    List<Income> findAllIncome(Users users);

    @Query("SELECT  SUM (i.amount) from Income i WHERE i.users=?1")
    Double findTotalIncome(Users users);

    @Query("SELECT  SUM (e.amount) from Income e WHERE e.users=?1")
    Double findTotalAmountIncome(Users users);
}
