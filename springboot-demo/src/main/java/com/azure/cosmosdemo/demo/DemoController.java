package com.azure.cosmosdemo.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private UserRepository repository;

  
    @RequestMapping(value="/users", method= RequestMethod.GET, headers="Accept=application/xml, application/json")
    @ResponseBody
    public Flux<User> users() {
        return repository.findAll();
    }

    @RequestMapping(value="/add/{name}", method= RequestMethod.GET, headers="Accept=application/xml, application/json")
    @ResponseBody
    public Mono<User> create(@PathVariable String name) {
       
        final User testUser = new User( name, "testLastName", "test address line one");

        // Save the User class to Azure Cosmos DB database.
        final Mono<User> saveUserMono = repository.save(testUser);

        
        return saveUserMono;
    }


}