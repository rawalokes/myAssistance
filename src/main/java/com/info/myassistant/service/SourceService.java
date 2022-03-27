package com.info.myassistant.service;

import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.dto.SourceDto;
import com.info.myassistant.shared.GenericService;

import java.util.List;

/**
 * @author rawalokes
 * Date:3/24/22
 * Time:11:50 AM
 */
public interface SourceService extends GenericService<SourceDto,Integer> {
    List<SourceDto> findAllSource();
    ResponseDto removeSource(Integer integer);
}
