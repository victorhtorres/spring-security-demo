package com.example.cashcard.controller;

import com.example.cashcard.meta.CurrentOwner;
import com.example.cashcard.payload.CashCardRequest;
import com.example.cashcard.repository.CashCardRepository;
import com.example.cashcard.entity.CashCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * The cash card REST API
 *
 */
@RestController
@RequestMapping("/cashcards")
public class CashCardController {
    private final CashCardRepository cashCardRepository;

    @Autowired
    public CashCardController(CashCardRepository cashCardRepository) {
        this.cashCardRepository = cashCardRepository;
    }

    @GetMapping("/{requestedId}")
    public ResponseEntity<CashCard> findById(@PathVariable Long requestedId) {
        return this.cashCardRepository.findById(requestedId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    private ResponseEntity<CashCard> createCashCard(@RequestBody CashCardRequest newCashCardRequest, UriComponentsBuilder ucb,
                                                    @CurrentOwner String owner) {

        CashCard savedCashCard = new CashCard(newCashCardRequest.getAmount(), owner);
        CashCard savedCashCardSaved = cashCardRepository.save(savedCashCard);
        URI locationOfNewCashCard = ucb
                .path("cashcards/{id}")
                .buildAndExpand(savedCashCardSaved.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).body(savedCashCardSaved);
    }

    @GetMapping
    public ResponseEntity<Iterable<CashCard>> findAll() {
        List<CashCard> result = cashCardRepository.findAll();
        return ResponseEntity.ok(result);
    }
}
