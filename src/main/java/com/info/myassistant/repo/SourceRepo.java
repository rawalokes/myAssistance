package com.info.myassistant.repo;

import com.info.myassistant.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepo extends JpaRepository<Source,Integer> {
}
