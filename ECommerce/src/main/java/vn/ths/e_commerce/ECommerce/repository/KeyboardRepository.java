package vn.ths.e_commerce.ECommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.ths.e_commerce.ECommerce.entity.Keyboard;

@Repository
public interface KeyboardRepository extends JpaRepository<Keyboard,Integer > {

    @Query("SELECT c FROM Keyboard c JOIN FETCH c.purchase JOIN FETCH c.cart JOIN FETCH c.keyboardDetail WHERE c.id=:id")
    public Keyboard findKeyboardByIdJoinFetch(Integer id);
}
