package ru.geekbrains.persist;

import ru.geekbrains.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(long id);

    void save(User username);

    void delete(long id);
}
