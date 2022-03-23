package com.info.myassistant.service;

import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.dto.TaskDto;
import com.info.myassistant.enums.TaskStatus;
import com.info.myassistant.model.Task;
import com.info.myassistant.shared.GenericService;

import java.time.LocalDate;
import java.util.List;

/**
 * @author rawalokes
 * Date:3/22/22
 * Time:3:53 PM
 */
public interface TaskService extends GenericService<TaskDto,Integer> {
   List<TaskDto> showCurrentPendingTask();
   List<TaskDto> showPendingTask();
   List<TaskDto> yesterdayTask();
   ResponseDto markTaskComplete(Integer id);
   ResponseDto findByDate(LocalDate date);

}
