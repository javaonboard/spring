package com.amarillo.data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinalResult {
    private int count;
    private BigDecimal totalRevenue;
    private BigDecimal totalExpense;
    private BigDecimal balance;
}
