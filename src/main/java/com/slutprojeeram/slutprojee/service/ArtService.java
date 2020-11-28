package com.slutprojeeram.slutprojee.service;


import com.slutprojeeram.slutprojee.model.Art;

import java.util.List;

public interface ArtService {

    public void saveArt(Art a);

    public List<Art> findAll();

    public Art findOne(String name);

    public Art findOneById(int id);

}
