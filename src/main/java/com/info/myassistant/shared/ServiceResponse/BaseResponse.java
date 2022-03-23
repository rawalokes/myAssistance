package com.info.myassistant.shared.ServiceResponse;

import com.info.myassistant.dto.responseDto.ResponseDto;

/**
 * @author rawalokes
 * Date:3/22/22
 * Time:4:01 PM
 */
public class BaseResponse {
    public ResponseDto successResponse(String message,Object data){
        return ResponseDto.builder()
                .status(true)
                .message(message)
                .data(data)
                .build();
    }
    public ResponseDto errorResponse(String message,Object data){
        return ResponseDto.builder()
                .status(false)
                .message(message)
                .data(data)
                .build();
    }


}
