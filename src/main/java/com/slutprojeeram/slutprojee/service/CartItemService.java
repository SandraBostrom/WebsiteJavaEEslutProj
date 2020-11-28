package com.slutprojeeram.slutprojee.service;


import com.slutprojeeram.slutprojee.model.Art;
import com.slutprojeeram.slutprojee.model.CartItem;
import com.slutprojeeram.slutprojee.model.ShoppingCart;
import com.slutprojeeram.slutprojee.model.User;

import java.util.List;

public interface CartItemService {

    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

    CartItem updateCartItem(CartItem cartItem);

    CartItem addArtToCartItem(Art art, User user, int quantity);
}

