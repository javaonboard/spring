package com.amarillo.controller;

import com.amarillo.Services.RevenueServiceImpl;
import com.amarillo.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RestController {

    @Autowired
    RevenueServiceImpl revenueService;

    @GetMapping(value = "/report")
    public String getAllRevenue(Model model){
        Iterable<Transaction> revs = revenueService.getAllRevenue();
        model.addAttribute("revenues", revs);
        return  "report";
    }


}
