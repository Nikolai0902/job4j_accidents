package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Authority;
import ru.job4j.accidents.repository.AuthorityRepositorySD;

@Service
@AllArgsConstructor
public class AuthorityServiceSD {

    private final AuthorityRepositorySD authorityRepository;

    public Authority findByAuthority(String authority) {
       return authorityRepository.findByAuthority(authority);
    }
}
