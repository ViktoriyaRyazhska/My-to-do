package com.softserve.itacademy.component.user;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(User user);

    User readById(long id);

    User update(User user);

    void delete(long id);

    List<User> getAll();

    Optional<User> findByUsername(String username);

    User getCurrentUser();
}
