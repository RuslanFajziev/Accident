package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.AccidentType;

public interface AccidentTypeSpringBootRepos extends CrudRepository<AccidentType, Integer> {
}