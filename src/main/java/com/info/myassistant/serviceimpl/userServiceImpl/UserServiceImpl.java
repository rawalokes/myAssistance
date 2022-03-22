package com.info.myassistant.serviceimpl.userServiceImpl;

import com.info.myassistant.dto.responseDto.ResponseDto;
import com.info.myassistant.dto.userDto.UserDto;
import com.info.myassistant.service.userService.UserService;
import com.info.myassistant.shared.controller.BaseResponse;

/**
 * @author rawalokes
 * Date:3/22/22
 * Time:4:58 PM
 */
public class UserServiceImpl extends BaseResponse implements UserService {
    @Override
    public ResponseDto create(UserDto userDto) {
        return null;
    }

    @Override
    public ResponseDto findByID(Integer integer) {
        return null;
    }

    @Override
    public ResponseDto findAllById(Integer integer) {
        return null;
    }
}
