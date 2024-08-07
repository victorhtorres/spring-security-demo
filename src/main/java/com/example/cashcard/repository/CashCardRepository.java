package com.example.cashcard.repository;

import com.example.cashcard.entity.CashCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The cash card repository.
 *
 * <p>Spring Data JDBC provides the implementation for this interface
 * at runtime.
 *
 */
public interface CashCardRepository extends CrudRepository<CashCard, Long> {

    @Query("select cc from CashCard cc where cc.owner = :#{authentication.name}")
    List<CashCard> findAll();
}
