package vn.ths.e_commerce.ECommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.ths.e_commerce.ECommerce.entity.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen,Integer > {

    @Query("SELECT c FROM Screen c JOIN FETCH c.purchase JOIN FETCH c.cart JOIN FETCH c.screenDetail WHERE c.id=:id")
    public Screen findScreenByIdJoinFetch(Integer id);
}
