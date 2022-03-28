package com.info.myassistant.utility;

import com.info.myassistant.model.Task;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author rawalokes
 * Date:3/27/22
 * Time:8:37 PM
 */
public class ExcelGenerator {
    public static ByteArrayInputStream dataToExcel(List<Task> tasks) throws IOException {
        String[] columns = {"Name", "Date", "Remarks", "Status"};
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Task");
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.AQUA.getIndex());
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < columns.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns[col]);
                cell.setCellStyle(headerCellStyle);
            }
            int rowIndex = 1;
            for (Task task : tasks) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(task.getName());
                row.createCell(1).setCellValue(task.getDate().toString());
                row.createCell(2).setCellValue(task.getRemarks());
                row.createCell(3).setCellValue(task.getTaskStatus().toString());
            }
            workbook.write(outputStream);


            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }

}
