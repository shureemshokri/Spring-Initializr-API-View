package com.example.todoapi.controller;

import com.example.todoapi.model.Todo;
import com.example.todoapi.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
	
	@Autowired
	private TodoService service;
	
	@GetMapping
    public List<Todo> getAll() throws IOException {
    	return service.getAll();
    }
    
	@GetMapping("/{id}")
    public ResponseEntity<Todo> getById(@PathVariable Long id) throws IOException {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
	@PostMapping
    public Todo create(@RequestBody Todo todo) throws IOException {
        return service.save(todo);
    }
    
	
	 @PutMapping("/{id}")
    public ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody Todo todo) throws IOException {
        return service.getById(id)
                .map(existingTodo -> {
                    todo.setId(id);
                    return ResponseEntity.ok(service.save(todo));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
	@DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) throws IOException {
    	return service.getById(id)
    			.map(existingTodo -> {
    				service.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    
    

}
