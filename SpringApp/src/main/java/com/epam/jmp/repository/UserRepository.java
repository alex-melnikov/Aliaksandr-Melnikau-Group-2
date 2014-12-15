package com.epam.jmp.repository;

import com.epam.jmp.data.User;

import java.util.List;


/**
 * Provides repository for manipulating users.
 *
 * @author Dzmitry_Naskou
 */
public interface UserRepository {

    /**
     * Gets the user by id.
     *
     * @param id the id
     * @return the user by id
     */
    User getUserById(Long id);

    /**
     * Creates the user.
     *
     * @param user the user
     * @return the user id
     */
    Long createUser(User user);

    /**
     * Update user.
     *
     * @param user the user
     */
    void updateUser(User user);

    /**
     * Gets the users.
     *
     * @return the users
     */
    List<User> getUsers();
}
