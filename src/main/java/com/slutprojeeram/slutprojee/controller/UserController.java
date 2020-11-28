package com.slutprojeeram.slutprojee.controller;


import com.slutprojeeram.slutprojee.model.Art;
import com.slutprojeeram.slutprojee.model.ShoppingCart;
import com.slutprojeeram.slutprojee.model.User;
import com.slutprojeeram.slutprojee.service.ArtService;
import com.slutprojeeram.slutprojee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService servDao;

    @Autowired
    private ArtService artService;


    @GetMapping("/home")
    public String showLoginPageUser(Model model) {

        model.addAttribute("user", new User());
        model.addAttribute("title", "Home Page");

        return "home";
    }

    @GetMapping("/registration")
    public String showRegistrationPageUser(Model model) {

        model.addAttribute("title", "Registration Page");

        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("/process")
    @Transactional
    public String showSuccessPageUser(@Valid @ModelAttribute("user") User user, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println(result.toString());
            return "registration";
        } else {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            user.setShoppingCart(shoppingCart);

            servDao.save(user);
            }

        return "login";
    }


    @GetMapping("/login")
    public String showLoginUser(Model model) {

        model.addAttribute("title", "title");

        return "login";
    }

    @RequestMapping("/userPage")
    public String userPage(@ModelAttribute("username") String username, Model model ) {
        model.addAttribute("username", username);
        return "index";
    }



    @RequestMapping("/artDetail")
    public String artDetail(@ModelAttribute("username") String username,
                            @PathParam("id") String name, Model model, Principal principal){

        model.addAttribute("username", username);
        if(principal != null){
            String email = principal.getName();
            User user = servDao.findByEmail(email);
            model.addAttribute("user", user);
        }
        Art art = artService.findOne(name);
        model.addAttribute("art", art);

        List<Integer> quantityList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        model.addAttribute("quantityList", quantityList );
        model.addAttribute("quantity", 1);

        return "artDetail";
    }
    @RequestMapping("/art")
    public String artShelf(@ModelAttribute("username") String username, Model model)

    {
    List<User> usernames = servDao.findAll();
    List<Art> artList = artService.findAll();
    model.addAttribute("artList", artList);
    model.addAttribute("username", username);
        return "artShelf";
    }

/*

    @RequestMapping(value = "/arts/{id}", method = RequestMethod.GET)
    public String artUseridShelf(  @PathVariable("id") int id)
    {
        List<Art> artList = artService.findAll();
        //model.addAttribute("artList", artList);
        //model.addAttribute("username", username);

        return "artShelf";
    }
*/
    @RequestMapping(value = "/newArt/{username}", method = RequestMethod.GET)
    public String artUseridShelf(  @PathVariable("username") String email, Model model)
    {
        String users = email;
        List<Art> artList = artService.findAll();
        model.addAttribute("artList", artList);
        model.addAttribute("username", email);
        return "artShelf";

    }
}

