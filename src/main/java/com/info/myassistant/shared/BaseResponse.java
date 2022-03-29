package com.info.myassistant.shared;

import com.info.myassistant.dto.ResponseDto;

/**
 * @author rawalokes
 * Date:3/22/22
 * Time:4:01 PM
 */
public class BaseResponse {
    /**
     *
     * @param message success message
     * @param data data to be sent
     * @return responseDto object with status true
     */
    public ResponseDto successResponse(String message,Object data){
        return ResponseDto.builder()
                .status(true)
                .message(message)
                .data(data)
                .build();
    }
    /**
     *
     * @param message error message
     * @param data null
     * @return responseDto object with status false
     */
    public ResponseDto errorResponse(String message,Object data){
        return ResponseDto.builder()
                .status(false)
                .message(message)
                .data(data)
                .build();
    }


}
