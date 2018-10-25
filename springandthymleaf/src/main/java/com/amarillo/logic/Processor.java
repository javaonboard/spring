package com.amarillo.logic;

import com.amarillo.Services.TransactionServiceImpl;
import com.amarillo.data.FinalResult;
import com.amarillo.data.ReportSummary;
import com.amarillo.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Processor {

    @Autowired
    TransactionServiceImpl transactionService;

    //fetching all requested days details from db and calculate the needed data.
    public List<ReportSummary> calculateSummaryReport(List<String> requestedDays){
        List<Transaction> tranList = new ArrayList<>();
        for(String day: requestedDays) tranList.addAll(transactionService.getTransactionByDay(day));
        Map<String,ReportSummary> map = new HashMap<>();
        for(int i=0;i<tranList.size();i++){
            ReportSummary rp = new ReportSummary();
            if(map.containsKey(tranList.get(i).getDay())){
                rp = map.get(tranList.get(i).getDay());
                rp = initializeReport(rp,tranList.get(i));
                map.put(tranList.get(i).getDay(),rp);
            }else{
                rp.setTotalOfRevenues(BigDecimal.valueOf(0));
                rp.setTotalOfExpenses(BigDecimal.valueOf(0));
                map.put(tranList.get(i).getDay(),initializeReport(rp,tranList.get(i)));}
        }
        return map.values().stream().collect(Collectors.toList());
    }

    //Calculate the data in each requested transaction.
    public ReportSummary initializeReport(ReportSummary rp,Transaction tr){
        rp.setDay(tr.getDay());
        rp.setCountOfDay(rp.getCountOfDay()+1);
        if(tr.getType().equals("REVENUE")) {
            rp.setTotalOfExpenses(rp.getTotalOfExpenses());
            rp.setTotalOfRevenues(rp.getTotalOfRevenues().add(tr.getAmount()));
            rp.setCountOfRevenues(rp.getCountOfRevenues()+1);
        }else{
            rp.setTotalOfExpenses(rp.getTotalOfExpenses().add(tr.getAmount()));
            rp.setTotalOfRevenues(rp.getTotalOfRevenues());
            rp.setCountOfExpenses(rp.getCountOfExpenses()+1);
        }
        rp.setRemainAmount(rp.getTotalOfRevenues().subtract(rp.getTotalOfExpenses()));
        return rp;
    }

    //calculate the Final Balance
    public FinalResult calculateTotal(List<ReportSummary> rps){
        FinalResult fr = new FinalResult();
        fr.setBalance(BigDecimal.valueOf(0));
        fr.setTotalRevenue(BigDecimal.valueOf(0));
        fr.setTotalExpense(BigDecimal.valueOf(0));
        for(ReportSummary rs: rps){
            fr.setTotalRevenue(fr.getTotalRevenue().add(rs.getTotalOfRevenues()));
            fr.setTotalExpense(fr.getTotalExpense().add(rs.getTotalOfExpenses()));
            fr.setCount(fr.getCount()+rs.getCountOfRevenues()+rs.getCountOfExpenses());
        }
        BigDecimal balance = fr.getTotalRevenue().subtract(fr.getTotalExpense());
        fr.setBalance(balance);
        return fr;
    }
}
