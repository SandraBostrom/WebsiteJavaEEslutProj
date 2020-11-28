
package com.slutprojeeram.slutprojee.security;

import com.slutprojeeram.slutprojee.model.User;
import com.slutprojeeram.slutprojee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CustomService implements UserDetailsService {

    @Autowired
    UserRepository repository;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user  = repository.findByEmail(email);


        if (user == null) {

            throw new UsernameNotFoundException("User not found") ;
        }


        CustomUserDetails customUser= new CustomUserDetails(user);

        return customUser;
    }

}
