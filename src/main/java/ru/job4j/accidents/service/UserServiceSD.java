package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.UserRepositorySD;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceSD {

    private final UserRepositorySD userRepository;

    public Optional<User> save(User user) {
        Optional<User> optionalUser = Optional.empty();
        try {
            optionalUser = Optional.of(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            log.error("Пользователь с этим username уже существует", e);
        }
        return optionalUser;
    }
}
