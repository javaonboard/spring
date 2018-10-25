package com.amarillo.data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReportSummary {

    private String day;
    private int countOfDay;
    private int countOfRevenues;
    private BigDecimal totalOfRevenues;
    private int countOfExpenses;
    private BigDecimal totalOfExpenses;
    private BigDecimal remainAmount;

}
