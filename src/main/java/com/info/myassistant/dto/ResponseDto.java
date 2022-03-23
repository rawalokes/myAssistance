package com.info.myassistant.dto;

import lombok.*;

/**
 * @author rawalokes
 * Date:3/22/22
 * Time:4:09 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseDto {
    private boolean status;
    private String message;
    private Object data;

}
