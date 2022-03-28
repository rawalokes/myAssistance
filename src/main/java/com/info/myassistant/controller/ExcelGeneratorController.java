package com.info.myassistant.controller;

import com.info.myassistant.model.Task;
import com.info.myassistant.repo.TaskRepo;
import com.info.myassistant.utility.ExcelGenerator;
import com.info.myassistant.utility.GetCurrentUserDetails;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author rawalokes
 * Date:3/27/22
 * Time:11:06 PM
 */
@RestController
public class ExcelGeneratorController {
    private final TaskRepo taskRepo;
    private final GetCurrentUserDetails currentUserDetails;

    public ExcelGeneratorController(TaskRepo taskRepo, GetCurrentUserDetails currentUserDetails) {
        this.taskRepo = taskRepo;
        this.currentUserDetails = currentUserDetails;
    }

    @GetMapping("download/task.xlsx")
    public ResponseEntity<InputStreamResource> excelStudentReport() throws IOException
    {
        List<Task> list = (List<Task>)taskRepo
                .findAllTaskByUser(currentUserDetails.getCurrentUser());
        ByteArrayInputStream in = ExcelGenerator.dataToExcel(list);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=task.xlsx");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

}
