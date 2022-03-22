package com.info.myassistant.repo.user;

import com.info.myassistant.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
