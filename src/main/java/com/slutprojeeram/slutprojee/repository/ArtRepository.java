package com.slutprojeeram.slutprojee.repository;

import com.slutprojeeram.slutprojee.model.Art;
import org.springframework.data.repository.CrudRepository;


public interface ArtRepository extends CrudRepository<Art, Integer> {

     Art findById(int id);

    Art findByName(String name);
}
