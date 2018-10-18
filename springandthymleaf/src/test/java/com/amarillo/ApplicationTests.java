package com.amarillo;

import com.amarillo.Services.RevenueService;
import com.amarillo.Services.RevenueServiceImpl;
import com.amarillo.entity.Transaction;
import com.amarillo.repository.RevenueRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Autowired
    RevenueRepository repository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testDB() {
        long revs = repository.count();
        assertEquals(4l, revs);
    }
}
