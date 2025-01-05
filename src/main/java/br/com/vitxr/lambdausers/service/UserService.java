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

//    public boolean updateCourse(Long id, User user) {
//        return getById(id).map(existingCourse -> {
//            users.remove(existingCourse);
//            users.add(user);
//            return true;
//        }).orElse(false);
//    }
//
//    // Delete a course by id
//    public boolean deleteCourse(int id) {
//        return users.removeIf(u -> u.getId() == id);
//    }

}
