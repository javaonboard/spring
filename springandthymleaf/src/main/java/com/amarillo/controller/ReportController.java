package com.amarillo.controller;

import com.amarillo.Services.TransactionServiceImpl;
import com.amarillo.constant.WeekDay;
import com.amarillo.data.CheckedDays;
import com.amarillo.data.FinalResult;
import com.amarillo.data.ReportSummary;

import com.amarillo.logic.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.*;


@Controller
public class ReportController {

    @Autowired
    TransactionServiceImpl transactionService;
    @Autowired
    Processor processor;


    @GetMapping(value="/report")
    public String loadReport(Model model){
        model.addAttribute("days",getDays());
        CheckedDays selected= new CheckedDays();
        model.addAttribute("selected", selected);
        List<ReportSummary> rps = new ArrayList<>();
        model.addAttribute("reportSummary",rps);
        FinalResult fr = new FinalResult();
        fr.setTotalExpense(BigDecimal.valueOf(0.00));
        fr.setTotalRevenue(BigDecimal.valueOf(0.00));
        fr.setBalance(BigDecimal.valueOf(0.00));
        model.addAttribute("total",fr);
        return "report";
    }

    @PostMapping(value="/getReport")
    public String calculate(@ModelAttribute("selected") CheckedDays checked,Model model){
        List<String> checkedDays = checked.getCheckedDays();
        List<ReportSummary> rpList = processor.calculateSummaryReport(checkedDays);
        model.addAttribute("days",getDays());
        model.addAttribute("total",processor.calculateTotal(rpList));
        model.addAttribute("reportSummary",rpList);
        return ("report");
    }


    public List<String> getDays(){
        List<String> days = new ArrayList<>();
        Arrays.asList(WeekDay.values()).stream().forEach(day->days.add(day.toString()));
        return days;
    }



}


