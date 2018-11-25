package ru.company.server.dao;

import ru.company.server.model.User;

public interface UserDetailsDao {

    /**
     * Получить User по name
     *
     * @param username
     * @return User
     */
    User findUserByUsername(String username);
}