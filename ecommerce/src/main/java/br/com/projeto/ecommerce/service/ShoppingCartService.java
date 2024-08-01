package br.com.projeto.ecommerce.service;

import br.com.projeto.ecommerce.models.ShoppingCart;
import br.com.projeto.ecommerce.models.User;
import br.com.projeto.ecommerce.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public ShoppingCart createCart(ShoppingCart shoppingCart, User user) {
        return null;
    }
}
