package com.amarillo.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Entity
@Table(name = "TRANSACTION")
@Data
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    private String day;
    private String time;
    private String description;
    @Column( precision=7, scale=2)
    //@Digits(integer = 7, fraction = 2)
    private BigDecimal amount;
    private String type;

    public Transaction(String day, String description, BigDecimal amount, String type){
        LocalDateTime now = LocalDateTime.now();
        time = now.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM));
        this.day = day;
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

}
