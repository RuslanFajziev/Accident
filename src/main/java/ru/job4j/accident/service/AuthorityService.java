package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Authority;
import ru.job4j.accident.repository.AuthorityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityService {
    private AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Authority findByAuthority(String authority) {
        Iterable<Authority> authorityIterable = authorityRepository.findAll();
        Authority rsl = new Authority();
        for (Authority elm : authorityIterable) {
            if (elm.getAuthority() == authority) {
                rsl = elm;
                break;
            }
        }
        return rsl;
    }

    public Authority findById(int id) {
        Authority rsl = new Authority();
        var findAuthority = authorityRepository.findById(id);
        if (findAuthority.isPresent()) {
            rsl = findAuthority.get();
        }
        return rsl;
    }

    public List<Authority> getAllAuthority() {
        List<Authority> lstAuthority = new ArrayList<Authority>();
        authorityRepository.findAll().forEach(lstAuthority::add);
        return lstAuthority;
    }
}