package com.amarillo.repository;

import com.amarillo.entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Long> {

    @Query(value = "SELECT * from TRANSACTION WHERE day = ?1",nativeQuery = true)
    List<Transaction> getAllTransactionByDay(@Param("day") String day);

}
