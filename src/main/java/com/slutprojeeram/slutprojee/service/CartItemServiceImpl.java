package com.slutprojeeram.slutprojee.service;

import com.slutprojeeram.slutprojee.model.*;
import com.slutprojeeram.slutprojee.repository.ArtToCartRepository;
import com.slutprojeeram.slutprojee.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;


    @Autowired
    private ArtToCartRepository artToCartRepository;

    @Override
    public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
        return cartItemRepository.findByShoppingCart(shoppingCart);
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {
        BigDecimal bigDecimal = new BigDecimal(cartItem.getArt().getPrice()).multiply(new BigDecimal(cartItem.getQuantity()));

        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        cartItem.setSubtotal(bigDecimal);

        cartItemRepository.save(cartItem);

        return cartItem;

    }

    @Override
    public CartItem addArtToCartItem(Art art, User user, int quantity) {
    List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());

    for(CartItem cartItem : cartItemList){
        if(art.getId() == cartItem.getArt().getId()){
            cartItem.setQuantity(cartItem.getQuantity()+quantity);
            cartItem.setSubtotal(new BigDecimal(art.getPrice()).multiply(new BigDecimal(quantity)));
            cartItemRepository.save(cartItem);
            return cartItem;
        }
    }
    CartItem cartItem = new CartItem();
    cartItem.setShoppingCart(user.getShoppingCart());
    cartItem.setArt(art);

    cartItem.setQuantity(quantity);
    cartItem.setSubtotal(new BigDecimal(art.getPrice()).multiply(new BigDecimal(quantity)));
    cartItemRepository.save(cartItem);

        ArtToCartItem artToCartItem = new ArtToCartItem();
        artToCartItem.setArt(art);
        artToCartItem.setCartItem(cartItem);
        artToCartRepository.save(artToCartItem);

        return cartItem;
    }
}
