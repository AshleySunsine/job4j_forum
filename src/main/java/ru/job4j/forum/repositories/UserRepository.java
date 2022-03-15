package ru.job4j.forum.repositories;

import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
private final Map<User, List<Authority>> users = new HashMap<>();

public void save(User user) {
    if (users.get(user) == null) {
        users.put(user, new ArrayList<Authority>());
        users.get(user).add(user.getAuthority());
    } else {
        System.out.println("Такой пользователь уже существует!");
    }

}

}
