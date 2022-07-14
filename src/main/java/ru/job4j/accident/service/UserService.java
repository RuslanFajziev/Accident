package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Boolean checkUser(User user) {
        Boolean rsl = false;
        String nameUser = user.getUsername();
        Iterable<User> userIterable = userRepository.findAll();
        for (User elm : userIterable) {
            if (elm.getUsername().equals(nameUser)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }
}