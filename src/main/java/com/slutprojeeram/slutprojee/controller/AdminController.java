
package com.slutprojeeram.slutprojee.controller;

import com.slutprojeeram.slutprojee.model.Art;
import com.slutprojeeram.slutprojee.model.User;
import com.slutprojeeram.slutprojee.service.ArtService;
import com.slutprojeeram.slutprojee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    UserService userService;


    @Autowired
    ArtService artService;

    @GetMapping("/registrationAdmin")
    public String showRegistrationPageUser(Model model) {

        model.addAttribute("title", "Registration Page");

        model.addAttribute("user", new User());

        return "registrationAdmin";
    }

   /* @PostMapping("/processAdmin")
    public String showSuccessPageUser(@Valid @ModelAttribute("user") User user, BindingResult result) {

        user.setRole("ROLE_ADMIN");
        user.setPassword(userService.enCryptedPassword(user));

        if (result.hasErrors()) {
            System.out.println(result.toString());
            return "registration";
        } else {

            userService.save(user);

        }

        return "login";
    }*/

    @GetMapping("/loginAdmin")
    public String showLogin(Model model) {
        model.addAttribute("title", "Login Page");

        return "loginAdmin";
    }
    @GetMapping("/adminHome")
    public String showHomepage() {

        return "home2";
    }
    @RequestMapping("/artListAdmin")
    public String artLists(Model model){
        List<Art> artList = artService.findAll();
        model.addAttribute("art", artList);

        return "artList";
    }
}
