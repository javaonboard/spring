package com.amarillo.controller;

import com.amarillo.Services.TransactionServiceImpl;
import com.amarillo.constant.WeekDay;
import com.amarillo.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


@Controller
public class RestController {

    @Autowired
    TransactionServiceImpl transactionService;

    @GetMapping(value = "/report")
    public String getAllTransaction(Model model){
        Iterable<Transaction> revs = transactionService.getAllTransaction();
        model.addAttribute("revenues", revs);
        return  "report";
    }

    @GetMapping(value = "/create")
    public String createTransactionView(Model model){
        model.addAttribute("tran", new Transaction());
        return "transaction";

    }


    @PostMapping(value="/create")
    public String createTransaction(@ModelAttribute("trform") Transaction transaction){
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM));
        transaction.setTime(time);
        transaction.setDay(WeekDay.values()[Integer.parseInt(transaction.getDay())].toString());
        transactionService.createTransaction(transaction);
        return "transaction";
    }

}
