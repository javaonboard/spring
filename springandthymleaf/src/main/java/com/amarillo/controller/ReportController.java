package com.amarillo.controller;

import com.amarillo.Services.TransactionServiceImpl;
import com.amarillo.constant.WeekDay;
import com.amarillo.data.CheckedDays;
import com.amarillo.data.FinalResult;
import com.amarillo.data.ReportSummary;

import com.amarillo.logic.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@Controller
public class ReportController {

    @Autowired
    TransactionServiceImpl transactionService;
    @Autowired
    Processor processor;

    List<ReportSummary> rpList;
    FinalResult finalResults = new FinalResult();

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
    public String calculate(@ModelAttribute("selected") CheckedDays checked,Model model)throws IOException{
        List<String> checkedDays = checked.getCheckedDays();
        rpList = processor.calculateSummaryReport(checkedDays);
        model.addAttribute("days",getDays());
        finalResults = processor.calculateTotal(rpList);
        model.addAttribute("total",finalResults);
        model.addAttribute("reportSummary",rpList);
        writhInJson(rpList,finalResults);
        return ("report");
    }

    public List<String> getDays(){
        List<String> days = new ArrayList<>();
        Arrays.asList(WeekDay.values()).stream().forEach(day->days.add(day.toString()));
        return days;
    }

    public void writhInJson(List<ReportSummary> rs, FinalResult fs)throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("Final Result", fs);
        for (ReportSummary report : rs) map.put(report.getDay(), report);
        JSONObject js = new JSONObject();
        map.entrySet().stream().forEach(k -> {
            try {
                js.put(k.getKey(), k.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        Path path = Paths.get("Report.txt");
        Files.deleteIfExists(path);
        FileWriter fw = new FileWriter(String.valueOf(path));
        try {
            fw.write(js.toString());
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
