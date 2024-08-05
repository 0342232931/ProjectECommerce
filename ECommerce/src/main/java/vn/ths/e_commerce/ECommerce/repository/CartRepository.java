package vn.ths.e_commerce.ECommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.ths.e_commerce.ECommerce.entity.Account;
import vn.ths.e_commerce.ECommerce.entity.Cart;
import vn.ths.e_commerce.ECommerce.entity.User;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer > {
    public Cart findCartByUser(User user);
    @Query("SELECT c FROM Cart c JOIN FETCH c.laptop JOIN FETCH c.mouses " +
            "JOIN FETCH c.screens JOIN FETCH c.keyboards JOIN FETCH c.user WHERE c.id=:id")
    public Cart findCartByIdJoinFetch(Integer id);
}
