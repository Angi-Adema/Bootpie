package com.revature.BootPie.controllers;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.BootPie.models.Consumer;
import com.revature.BootPie.services.ConsumerService;

@RestController
@RequestMapping("consumer")
public class ConsumerController {
    
    private ConsumerService consumerService;

    @Autowired
    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    // POST request to login a user. (http://localhost:8080/consumer/login)
    @PostMapping("login")
    public ResponseEntity<Void> login(@RequestBody Consumer consumer) throws AuthenticationException {
        consumerService.login(consumer.getUsername(), consumer.getPassword());
        return ResponseEntity.noContent()
                            .header("username", consumer.getUsername())
                            .build();
    }

    // POST request to register a new user. (http://localhost:8080/consumer/register)
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody Consumer consumer) {
        consumerService.register(consumer);
        return ResponseEntity.status(HttpStatus.CREATED)
                            .body("Successfully registered");
    }

    // PATCH request to order a pie. (http://localhost:8080/consumer/order?pieName=Cherry)
    @PatchMapping("order")
    public ResponseEntity<String> order(@RequestParam String pieName, @RequestHeader("username") String username) {
        consumerService.order(username, pieName);
        return ResponseEntity.accepted()
                            .body(username + " ordered " + pieName + " pie.");
    }

    // Finding everyone and filtering by the last pie they ordered.
    @GetMapping("byLastPie/{pieName}")
    public ResponseEntity<List<Consumer>> findConsumerByLastPie(@PathVariable String pieName) {
        return ResponseEntity.ok()
                            .body(consumerService.findAllConsumersByLastPie(pieName));
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUnauthorized(AuthenticationException ex) {
        return ex.getMessage();
    }
}
