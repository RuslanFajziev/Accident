package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Authority;

import java.util.Optional;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    Optional<Authority> findAuthorityByAuthority(String authority);
}