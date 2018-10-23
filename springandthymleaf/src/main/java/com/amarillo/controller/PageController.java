package com.amarillo.controller;

import com.amarillo.Services.TransactionServiceImpl;
import com.amarillo.constant.WeekDay;
import com.amarillo.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Optional;

@Controller
public class PageController {

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
    public String loadTransactionPage(Model model,Transaction tran){
        model.addAttribute("trform", new Transaction());
        Iterable<Transaction> trs = transactionService.getAllTransaction();
        model.addAttribute("transactions", trs);
        model.addAttribute("tran",tran);
        return "transaction";
    }


    @PostMapping(value="/create")
    public String createTransaction(@ModelAttribute("trform") Transaction transaction){
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM));
        transaction.setTime(time);
        transaction.setDay(WeekDay.values()[Integer.parseInt(transaction.getDay())].toString());
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
    public String update(@PathVariable String id,Model model) {
        System.out.println("here" + id);
        long tid = Long.parseLong(id);
        Iterable<Transaction> trs01 = transactionService.getAllTransaction();
        Transaction tran = transactionService.getTransactionById(tid).get();
        model.addAttribute("myObject", trs01);
        return "/transaction::modalContents";
    }



    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public String saveUpdate(@PathVariable String id, @ModelAttribute("myObject") Transaction updateTr) {
        long tid = Long.parseLong(id);
        Transaction updated = transactionService.getTransactionById(tid).get();
        updated.setAmount(updateTr.getAmount());
        updated.setDescription(updateTr.getDescription());
        transactionService.updateTransaction(updated);
        return "redirect:/transaction";
    }

}
