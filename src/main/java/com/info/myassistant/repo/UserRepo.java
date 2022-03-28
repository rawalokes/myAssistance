package com.info.myassistant.repo;

import com.info.myassistant.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {
    Users findByEmail(String email);


}
