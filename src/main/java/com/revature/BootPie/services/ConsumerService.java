package com.revature.BootPie.services;

import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.BootPie.exceptions.ResourceNotFoundException;
import com.revature.BootPie.models.Consumer;
import com.revature.BootPie.repositories.ConsumerRepository;

@Service
public class ConsumerService {
    
    // private List<Consumer> consumerList = new ArrayList<>();
    private ConsumerRepository consumerRepository;

    private PieService pieService;

    @Autowired
    public ConsumerService(PieService pieService, ConsumerRepository consumerRepository) {
        this.pieService = pieService;
        this.consumerRepository = consumerRepository;
    }

    // {
    //     Consumer consumer = new Consumer("admin", "admin", null);
    //     consumerList.add(consumer);
    // }

    public void register(Consumer newConsumer) {

        consumerRepository.save(newConsumer);

        // No longer need to add to a list, we can just save the consumer to the database.
        //consumerList.add(newConsumer);
    }

    public void order(String username, String pieName) {

        Consumer consumer = consumerRepository.findById(username).orElseThrow();
        consumer.setLastPie(pieService.findPie(pieName));
        consumerRepository.save(consumer);

        // for (Consumer consumer : consumerList) {
        //     if (consumer.getUsername().equals(username)) {
        //         consumer.setLastPie(pieService.findPie(pieName));
        //         return;
        //     }
        // }
        // throw new ResourceNotFoundException(username + " was not found in the list. Please check username and try again.");
    }

    // TODO: Custom query for login functionality.
    public void login(String username, String password) throws AuthenticationException {

        consumerRepository.findByUsernameAndPassword(username, password);

        // for (Consumer consumer : consumerList) {
        //     if (consumer.getUsername().equals(username) && consumer.getPassword().equals(password)) {
        //         return;
        //     }
        // }
        // throw new AuthenticationException("Check username and password credentials as they are invalid.");
    }
}
