package br.com.vitxr.lambdausers.controller;

import br.com.vitxr.lambdausers.dto.User;
import br.com.vitxr.lambdausers.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Slf4j
public class UsersController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        var users = service.getAll();

        System.out.println(users);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable Long id){
        var user = service.getById(id);

        System.out.println(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> post(@RequestBody User user){
        System.out.println(user);

        var id = service.create(user);

        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
