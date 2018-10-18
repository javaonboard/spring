package com.amarillo;

import com.amarillo.entity.Transaction;
import com.amarillo.repository.RevenueRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ApplicationTests {
    @Autowired
    RevenueRepository repository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testDBcount() {
        long revs = repository.count();
        assertEquals(4l, revs);
    }

    @Test
    public void testDBall() {
        Iterable<Transaction> it = repository.findAll();
        it.forEach(t -> log.info("\n" + t.toString()));
    }
}
