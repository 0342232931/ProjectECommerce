package vn.ths.e_commerce.ECommerce.service.serviceInterface;

import vn.ths.e_commerce.ECommerce.entity.Cart;
import vn.ths.e_commerce.ECommerce.entity.User;

import java.util.List;

public interface CartService {
    public Cart add(Cart cart);
    public void deleteById(Integer id);
    public List<Cart> getAll();
    public Cart update(Cart cart);
    public Cart getById(Integer id);
    public Cart getByUser(User user);
}
