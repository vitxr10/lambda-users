package br.com.vitxr.lambdausers.service;

import br.com.vitxr.lambdausers.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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

    public Optional<User> update(Long id, User updatedUser) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(existingUser -> {
                    existingUser.setName(updatedUser.getName());
                    existingUser.setEmail(updatedUser.getEmail());
                    existingUser.setPassword(updatedUser.getPassword());
                    existingUser.setAge(updatedUser.getAge());
                    existingUser.setWeight(updatedUser.getWeight());
                    existingUser.setHeigth(updatedUser.getHeigth());
                    return existingUser;
                });
    }

    public boolean delete(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }

}
