package ru.job4j.forum.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Authority;

public interface AuthoritiesRepository extends CrudRepository<Authority, Long> {
    Authority findByAuthority(String authority);
}
