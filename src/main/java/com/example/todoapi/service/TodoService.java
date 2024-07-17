package com.example.todoapi.service;

import com.example.todoapi.model.Todo;
import com.example.todoapi.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository repository;

    public List<Todo> getAll() {
        return repository.findAll();
    }

    public Optional<Todo> getById(Long id) {
        return repository.findById(id);
    }

    public Todo save(Todo todo) {
        return repository.save(todo);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    

	public TodoService() {
		// TODO Auto-generated constructor stub


}
}


