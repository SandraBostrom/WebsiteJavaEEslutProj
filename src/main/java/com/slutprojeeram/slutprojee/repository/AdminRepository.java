package com.slutprojeeram.slutprojee.repository;


import com.slutprojeeram.slutprojee.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {

    public Admin findByEmail(String email);



}


