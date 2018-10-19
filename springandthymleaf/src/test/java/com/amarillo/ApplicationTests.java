package com.amarillo;

import com.amarillo.entity.Transaction;
import com.amarillo.repository.RevenueRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@Transactional
public class ApplicationTests {
    @Autowired
    RevenueRepository repository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testDBcount() {
        long revs = repository.count();
        assertEquals(4L, revs);
    }

    @Test
    public void testDBall() {
        Iterable<Transaction> it = repository.findAll();
        it.forEach(t -> log.info("\n" + t.toString()));
    }

    @Test
    public void testDfindById() {
        Optional<Transaction> transaction = repository.findById(1003L);
        if(transaction.isPresent()) {
            assertEquals("MONDAY", transaction.get().getDay());
        } else {
            fail("1003 not found");
        }
    }

    @Test
    public void testDBAdd() {
        Transaction transaction = new Transaction
            ("WENDESDAY", "some description here.",
                new BigDecimal(25.00), "E");
        repository.save(transaction);
        assertEquals(5, repository.count());
    }

    @Test
    public void testDBdelete() {
        Optional<Transaction> t = repository.findById(1003L);
        if(t.isPresent()) {
            repository.delete(t.get());
            assertFalse(repository.existsById(1003L));
        }
    }

}
