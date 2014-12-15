package com.epam.jmp.api.impl;

import com.epam.jmp.api.UserService;
import com.epam.jmp.data.User;
import com.epam.jmp.repository.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    /* (non-Javadoc)
     * @see org.shop.api.UserService#registerUser(org.shop.data.User)
     */
    @Override
    public Long registerUser(User user) {
        return repository.createUser(user);
    }

    /* (non-Javadoc)
     * @see org.shop.api.UserService#getUserById(java.lang.Long)
     */
    @Override
    public User getUserById(Long userId) {
        return repository.getUserById(userId);
    }

    /* (non-Javadoc)
     * @see org.shop.api.UserService#updateUserProfile(org.shop.data.User)
     */
    @Override
    public void updateUserProfile(User user) {
        repository.updateUser(user);
    }

    /* (non-Javadoc)
     * @see org.shop.api.UserService#getUsers()
     */
    @Override
    public List<User> getUsers() {
        return repository.getUsers();
    }
}
