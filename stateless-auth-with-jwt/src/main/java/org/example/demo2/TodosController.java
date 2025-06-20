package org.example.demo2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class TodosController {

    static List<Todo> todos = List.of(
            new Todo(1, "Learn Spring Boot", "Complete the Spring Boot tutorial", true),
            new Todo(2, "Build a REST API", "Create a RESTful API using Spring Boot", false)
    );

    @GetMapping("/todos")
    public ResponseEntity<?> getTodos(Principal principal) {
        System.out.println("Principal: " + principal.getName());
        if (todos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(todos);
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody Todo todo) {
        todos.add(todo);
        return ResponseEntity.status(201).body(todo);
    }

    // Add more methods as needed for your application

}
