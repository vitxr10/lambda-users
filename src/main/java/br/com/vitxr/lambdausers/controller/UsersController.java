package br.com.vitxr.lambdausers.controller;

import br.com.vitxr.lambdausers.dto.User;
import br.com.vitxr.lambdausers.service.UserService;
import lombok.extern.slf4j.Slf4j;
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

        log.info("Listando todos os usuários", users);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable Long id){
        var user = service.getById(id);

        log.info("Usuário encontrado", user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> post(@RequestBody User user){
        log.info("Recebido o payload de novo usuário", user);

        var id = service.create(user);

        log.info("Criado novo usuário", user);

        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }


}
