package com.slutprojeeram.slutprojee.controller;

import com.slutprojeeram.slutprojee.model.Art;
import com.slutprojeeram.slutprojee.model.CartItem;
import com.slutprojeeram.slutprojee.model.ShoppingCart;
import com.slutprojeeram.slutprojee.model.User;
import com.slutprojeeram.slutprojee.service.ArtService;
import com.slutprojeeram.slutprojee.service.CartItemService;
import com.slutprojeeram.slutprojee.service.ShoppingCartService;
import com.slutprojeeram.slutprojee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/userPage")
public class ShoppingCartController{

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ArtService artService;


    @RequestMapping("/cart")
    public String shoppingCart(@ModelAttribute("emailID") String emailID,
            Model model){

        User user = userService.findByEmail("test@test.com");
        ShoppingCart shoppingCart = user.getShoppingCart();

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        shoppingCartService.updateShoppingCart(shoppingCart);

        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("shoppingcart", shoppingCart);
        return "shoppingCart";
    }

    @RequestMapping("/addItem")
    public String addItem(
            @ModelAttribute("artID") String artID,
            //@ModelAttribute("username") String username, eller username vad du nu bestämmer...
            @ModelAttribute("quantity") String quantity,
            Model model
    ){
        // User user = userService.findByEmail(username);
     User user = userService.findByEmail("test@test.com");
    artService.findAll();
    int i = Integer.parseInt(artID);
     Art art = artService.findOneById(i);
    //Art art = artService.findOneById(3);
    int apa = art.getId();
     art = artService.findOneById(art.getId());


     CartItem cartItem = cartItemService.addArtToCartItem(art, user, Integer.parseInt(quantity));
    model.addAttribute("addArtSuccess", true);
    String banan = "/artDetail?id="+art.getId();


     return "forward:/userPage/cart";
    }


}

