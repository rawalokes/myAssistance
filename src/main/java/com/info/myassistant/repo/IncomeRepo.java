package com.info.myassistant.repo;

import com.info.myassistant.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepo extends JpaRepository<Income,Integer> {
}
