package com.iam.service.controller;

import com.iam.service.mapper.PageOption;
import com.iam.service.service.BaseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<T, ID, CreateDTO, UpdateDTO> {
    abstract protected BaseService<T, ID, CreateDTO, UpdateDTO> getService();

    @GetMapping
    public ResponseEntity<List<T>> getAll(
            @RequestParam(defaultValue = "0") int start,
            @RequestParam(defaultValue = "30") int end,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ){
        PageOption pageOption = new PageOption(start, end, sortBy, sortOrder);
        List<T> listOfData = getService().findAll(pageOption);
        return ResponseEntity.ok(listOfData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable("id") ID id){
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        T entity = getService().findById(id);
        return ResponseEntity.ok(entity);
    }

    @PostMapping
    public ResponseEntity<T> createResource(@Valid @RequestBody CreateDTO createDto){
        T entity = getService().createResource(createDto);
        return ResponseEntity.ok(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> updateResource(@PathVariable ID id, @Valid @RequestBody UpdateDTO updateDto){
        T entity = getService().updateResource(id, updateDto);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable ID id){
        getService().deleteResource(id);
        return ResponseEntity.noContent().build();
    }
}
