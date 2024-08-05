package vn.ths.e_commerce.ECommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.ths.e_commerce.ECommerce.entity.Mouse;

@Repository
public interface MouseRepository extends JpaRepository<Mouse,Integer > {

    @Query("SELECT c FROM Mouse c JOIN FETCH c.purchase JOIN FETCH c.cart JOIN FETCH c.mouseDetail WHERE c.id=:id")
    public Mouse findMouseByIdJoinFetch(Integer id);
}
