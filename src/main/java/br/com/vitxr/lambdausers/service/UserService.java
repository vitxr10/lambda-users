package br.com.vitxr.lambdausers.service;

import br.com.vitxr.lambdausers.dto.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public Long create(User user){
        users.add(user);

        return user.getId();
    }

    public List<User> getAll(){
        return users;
    }

    public Optional<User> getById(Long id){
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

}
