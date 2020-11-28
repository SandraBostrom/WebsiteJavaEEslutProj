package com.slutprojeeram.slutprojee.service;

import com.slutprojeeram.slutprojee.model.Art;
import com.slutprojeeram.slutprojee.repository.ArtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtServiceImpl implements ArtService{

    @Autowired
    ArtRepository artRepository;

    @Override
    public List<Art> findAll() {
        System.out.println(artRepository.findAll());
        List<Art> apa = (List<Art>) artRepository.findAll();
        return apa;
        //return (List<Art>) artRepository.findAll();
    }

    @Override
    public Art findOne(String name) {
        return artRepository.findByName(name);
    }

    @Override
    public Art findOneById(int id) {
    return artRepository.findById(id);
    }


    @Override
    public void saveArt(Art s) {

        artRepository.save(s);

    }
}
