package com.slutprojeeram.slutprojee.service;



import com.slutprojeeram.slutprojee.model.User;
import com.slutprojeeram.slutprojee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private  BCryptPasswordEncoder encrypt;

    @Autowired
    private UserRepository repo;

    @Override
    public void save(User user) {

        repo.save(user);


    }

    @Override
    public String enCryptedPassword(User user) {

        return encrypt.encode(user.getPassword());
    }

    @Override
    public User findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public User findOne(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        System.out.println(repo.findAll());
        List<User> apa = (List<User>) repo.findAll();
        return apa;
        //return (List<Art>) artRepository.findAll();
    }
}

