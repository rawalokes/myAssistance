package com.info.myassistant.repo;


import com.info.myassistant.model.Source;
import com.info.myassistant.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceRepo extends JpaRepository<Source,Integer> {
    @Query("SELECT s FROM Source s WHERE s.users = ?1 and s.deleteStatus = ?2 ")
    List<Source> findAllSource( Users users,Boolean deleteStatus);
}
