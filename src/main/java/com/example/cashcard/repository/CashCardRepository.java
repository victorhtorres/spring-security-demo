package com.example.cashcard.repository;

import com.example.cashcard.entity.CashCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * The cash card repository.
 *
 * <p>Spring Data JDBC provides the implementation for this interface
 * at runtime.
 *
 */
public interface CashCardRepository extends CrudRepository<CashCard, Long> {
    List<CashCard> findByOwner(String owner);

    default List<CashCard> findAll() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String owner = authentication.getName();
        return findByOwner(owner);
    }
}
