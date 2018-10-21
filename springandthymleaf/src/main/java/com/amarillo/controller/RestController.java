package com.amarillo.controller;

import com.amarillo.Services.TransactionServiceImpl;
import com.amarillo.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class RestController {

    @Autowired
    TransactionServiceImpl revenueService;

    @GetMapping(value = "/report")
    public String getAllRevenue(Model model){
        Iterable<Transaction> revs = revenueService.getAllRevenue();
        model.addAttribute("revenues", revs);
        return  "report";
    }

    @PostMapping(value="/create")
    public String create(@ModelAttribute Transaction transaction, BindingResult result, Model model){

        System.out.println(transaction.getAmount());

        return "transaction";
    }

}
