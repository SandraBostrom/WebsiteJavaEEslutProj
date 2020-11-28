package com.slutprojeeram.slutprojee.repository;

import com.slutprojeeram.slutprojee.model.CartItem;
import com.slutprojeeram.slutprojee.model.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartItemRepository extends CrudRepository<CartItem, Integer> {

        List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
}
