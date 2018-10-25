package com.amarillo.controller;

import com.amarillo.Services.TransactionServiceImpl;
import com.amarillo.constant.WeekDay;
import com.amarillo.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class TransactionController {

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    TransactionServiceImpl transactionService;

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("appName",appName);
        return "home";
    }

    @GetMapping("/transaction")
    public String loadTransactionPage(Model model, Transaction tran){
        Iterable<Transaction> trs = transactionService.getAllTransaction();
        model.addAttribute("trform", new Transaction());
        model.addAttribute("days",getDays());
        model.addAttribute("transactions", trs);
        model.addAttribute("next", "/create");
        return "transaction";
    }

    @PostMapping(value="/create")
    public String createTransaction(@ModelAttribute("trform") Transaction transaction){
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM));
        transaction.setTime(time);
        transactionService.createTransaction(transaction);
        return "redirect:transaction";
    }

    @GetMapping(value="/delete/{id}")
    public String deleteTransaction(@PathVariable String id){
        long tid = Long.parseLong(id);
        transactionService.deleteTransactionById(tid);
        return "redirect:/transaction";
    }

    @RequestMapping(value = "/update/{id}")
    public String update(@PathVariable String id, Model model) {
        Iterable<Transaction> trs = transactionService.getAllTransaction();
        model.addAttribute("trform", transactionService.getTransactionById(Long.parseLong(id)).get());
        model.addAttribute("transactions", trs);
        model.addAttribute("days",getDays());
        model.addAttribute("next", "/update/transaction/" + id);
        return ("transaction");
    }

    @RequestMapping(value = "/update/transaction/{id}", method = RequestMethod.POST)
    public String saveUpdate(@PathVariable String id, @ModelAttribute("myObject") Transaction updated) throws Exception {
        transactionService.updateTransaction(id,updated);
        return "redirect:/transaction";
    }


    public List<String> getDays(){
        List<String> days = new ArrayList<>();
        Arrays.asList(WeekDay.values()).stream().forEach(day->days.add(day.toString()));
        return days;
    }
}
