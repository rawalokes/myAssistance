package com.info.myassistant.serviceimpl.userServiceImpl;

import com.info.myassistant.dto.responseDto.ResponseDto;
import com.info.myassistant.dto.userDto.UserDto;
import com.info.myassistant.model.user.User;
import com.info.myassistant.repo.user.UserRepo;
import com.info.myassistant.service.userService.UserService;
import com.info.myassistant.shared.ServiceResponse.BaseResponse;

import java.util.Optional;

/**
 * @author rawalokes
 * Date:3/22/22
 * Time:4:58 PM
 */
public class UserServiceImpl extends BaseResponse implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public ResponseDto create(UserDto userDto) {
        try {
            //convert user into UserDto
            User user= new User(userDto);


            return successResponse("User Register Successfully",null);
        }catch (Exception e){
            return errorResponse("Something Went Wrong Please Try Again",null);
        }
    }

    @Override
    public ResponseDto findByID(Integer integer) {
        Optional<User> user= userRepo.findById(integer);
        if (user.isPresent()){
            return successResponse("",user.get());
        }
        else {
            return errorResponse("User Not Found",null);
        }

    }

}
