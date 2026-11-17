package com.example.tourists.controller;

import com.example.tourists.model.Tourist;
import com.example.tourists.repository.TouristRepository;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/tourists")
public class TouristController {
    private final TouristRepository repository;
    public TouristController(TouristRepository repository) { this.repository = repository; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tourist create(@Valid @RequestBody Tourist tourist) {
        if (repository.existsByEmail(tourist.getEmail()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        return repository.save(tourist);
    }

    @GetMapping
    public List<Tourist> getAll() { return repository.findAll(); }

    @GetMapping("/{id}")
    public Tourist getOne(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Tourist record not found"));
    }

    @PutMapping("/{id}")
    public Tourist update(@PathVariable Long id, @Valid @RequestBody Tourist updated) {
        Tourist existing = getOne(id);
        if (!existing.getEmail().equals(updated.getEmail()) && repository.existsByEmail(updated.getEmail()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setDestination(updated.getDestination());
        existing.setCountry(updated.getCountry());
        existing.setVisitDate(updated.getVisitDate());
        existing.setNotes(updated.getNotes());
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { repository.delete(getOne(id)); }
}
