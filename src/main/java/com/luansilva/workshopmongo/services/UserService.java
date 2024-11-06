package com.luansilva.workshopmongo.services;


import com.luansilva.workshopmongo.domain.User;
import com.luansilva.workshopmongo.dto.UserDTO;
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

    public User insert(User obj) {
        return repo.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    public User update(User obj) {
        Optional<User> newObj = repo.findById(obj.getId());
        if (newObj.isPresent()) {
            updateData(Optional.of(newObj.get()), obj);
        } else {
            throw new ObjectNotFoundException("Usuário não encontrado.");
        }
        return repo.save(newObj.get());
    }

    private void updateData(Optional<User> newObj, User obj) {
        newObj.get().setName(obj.getName());
        newObj.get().setEmail(obj.getEmail());
    }

    public User fromDTO(UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }

}
