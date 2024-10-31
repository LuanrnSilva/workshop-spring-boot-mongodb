package com.luansilva.workshopmongo.services;


import com.luansilva.workshopmongo.domain.User;
import com.luansilva.workshopmongo.repository.UserRepository;
import com.luansilva.workshopmongo.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll() {
        return repo.findAll();
    }

    public User findById(String id) {
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }


}
