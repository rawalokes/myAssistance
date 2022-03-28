package com.info.myassistant.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface HighChartService {
//    Map<LocalDate,Double> expenseGraph();
     void expenseGraph();
    Map<Integer,Integer> taskPieChart();

}
