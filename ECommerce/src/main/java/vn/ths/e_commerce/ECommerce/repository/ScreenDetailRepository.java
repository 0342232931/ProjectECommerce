package vn.ths.e_commerce.ECommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.ths.e_commerce.ECommerce.entity.ScreenDetail;

@Repository
public interface ScreenDetailRepository extends JpaRepository<ScreenDetail,Integer > {

    @Query("SELECT c FROM ScreenDetail c JOIN FETCH c.screen WHERE c.id=:id")
    public ScreenDetail findScreenDetailByIdJoinFetch(Integer id);
}
