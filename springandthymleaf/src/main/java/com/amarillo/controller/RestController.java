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

/*    @GetMapping(value = "/transaction")
    public String getAllTransaction(Model model){
        Iterable<Transaction> trs = transactionService.getAllTransaction();
        model.addAttribute("transactions", trs);
        return  "transaction";
    }*/




}
