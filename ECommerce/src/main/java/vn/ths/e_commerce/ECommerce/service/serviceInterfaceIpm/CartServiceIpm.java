package vn.ths.e_commerce.ECommerce.service.serviceInterfaceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ths.e_commerce.ECommerce.entity.Cart;
import vn.ths.e_commerce.ECommerce.entity.User;
import vn.ths.e_commerce.ECommerce.repository.CartRepository;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.CartService;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceIpm implements CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartServiceIpm(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public Cart add(Cart cart) {
        return cartRepository.saveAndFlush(cart);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        cartRepository.deleteById(id);
    }

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    @Transactional
    public Cart update(Cart cart) {
        Optional<Cart> optionalCart = cartRepository.findById(cart.getId());
        if(optionalCart.isPresent()){
            return cartRepository.saveAndFlush(cart);
        }
        return null;
    }

    @Override
    public Cart getById(Integer id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if(optionalCart.isPresent()){
            return cartRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Cart getByUser(User user) {
        return cartRepository.findCartByUser(user);
    }
}
