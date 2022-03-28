package com.info.myassistant.serviceimpl;

import com.info.myassistant.enums.TaskStatus;
import com.info.myassistant.model.Expense;
import com.info.myassistant.model.Task;
import com.info.myassistant.model.Users;
import com.info.myassistant.repo.ExpenseRepo;
import com.info.myassistant.repo.TaskRepo;
import com.info.myassistant.service.HighChartService;
import com.info.myassistant.shared.BaseResponse;
import com.info.myassistant.utility.GetCurrentUserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author rawalokes
 * Date:3/28/22
 * Time:12:45 AM
 */
@Service
public class HighChartServiceImpl extends BaseResponse  {

    private final GetCurrentUserDetails currentUserDetails;
    private final TaskRepo taskRepo;
    private final ExpenseRepo expenseRepo;

    public HighChartServiceImpl(GetCurrentUserDetails currentUserDetails
            , TaskRepo taskRepo, ExpenseRepo expenseRepo) {
        this.currentUserDetails = currentUserDetails;
        this.taskRepo = taskRepo;
        this.expenseRepo = expenseRepo;
    }


    public void expenseGraph() {

    }


}
