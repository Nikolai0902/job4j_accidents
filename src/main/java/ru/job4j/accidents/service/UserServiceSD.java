package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.UserRepositorySD;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceSD {

    private final UserRepositorySD userRepository;

    public Optional<User> save(User user) {
        return Optional.of(userRepository.save(user));
    }
}
