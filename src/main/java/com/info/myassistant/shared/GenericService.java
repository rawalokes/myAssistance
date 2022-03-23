package com.info.myassistant.shared;

import com.info.myassistant.dto.ResponseDto;

public interface GenericService <T,ID>{
    ResponseDto create (T t);
    ResponseDto findByID(ID id);

}
