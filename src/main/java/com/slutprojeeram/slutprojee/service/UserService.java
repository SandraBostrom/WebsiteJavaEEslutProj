
package com.slutprojeeram.slutprojee.service;


import com.slutprojeeram.slutprojee.model.User;

import java.util.List;

public interface UserService {


    public void save(User user);

    public String enCryptedPassword(User user);

    public User findByEmail(String email);

    public User findOne(String email);

    public List<User> findAll();

}
