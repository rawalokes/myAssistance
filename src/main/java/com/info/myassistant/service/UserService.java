package com.info.myassistant.service;

import com.info.myassistant.dto.UserDto;
import com.info.myassistant.shared.GenericService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends GenericService<UserDto, Integer>, UserDetailsService {



}
