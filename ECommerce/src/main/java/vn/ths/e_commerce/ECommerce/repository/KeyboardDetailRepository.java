package vn.ths.e_commerce.ECommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.ths.e_commerce.ECommerce.entity.KeyboardDetail;

@Repository
public interface KeyboardDetailRepository extends JpaRepository<KeyboardDetail,Integer > {

    @Query("SELECT c FROM KeyboardDetail c JOIN FETCH c.keyboard WHERE c.id=:id")
    public KeyboardDetail findKeyboardDetailByIdJoinFetch(Integer id);
}
