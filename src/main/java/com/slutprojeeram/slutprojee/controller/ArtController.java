package com.slutprojeeram.slutprojee.controller;

import com.slutprojeeram.slutprojee.model.Art;
import com.slutprojeeram.slutprojee.service.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Controller
public class ArtController {


    @Autowired
    private ArtService artDao;

    @RequestMapping(value = "/adminHome/add", method = RequestMethod.GET)
    public String addArt(Model model) {
        Art art = new Art();
        model.addAttribute("art", art);
        return "addArt";
    }


    @RequestMapping(value = "/adminHome/add", method = RequestMethod.POST)
    public String addArtPost(
            @ModelAttribute("art") Art art, HttpServletRequest request) {
        artDao.saveArt(art);

        MultipartFile artImage = art.getArtImage();

        try {
            byte[] bytes = artImage.getBytes();
            String name = art.getId() + ".png";
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/art/" + name)));
            stream.write(bytes);
            stream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:artList";
    }

    @RequestMapping("/adminHome/artList")
    public String artLists(Model model){
        List<Art> art = artDao.findAll();
        model.addAttribute("art", art);
        return "artList";
    }

    @RequestMapping("/adminHome/artInfo")
    public String artInfo(@PathParam("id") String name, Model model){
        Art art = artDao.findOne(name);
        model.addAttribute("art", art);

        return "artInfo";
    }


}
