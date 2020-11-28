
package com.slutprojeeram.slutprojee.repository;

import com.slutprojeeram.slutprojee.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

     User findByEmail(String email);







}
