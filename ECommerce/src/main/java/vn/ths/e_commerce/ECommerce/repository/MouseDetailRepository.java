package vn.ths.e_commerce.ECommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.ths.e_commerce.ECommerce.entity.MouseDetail;

@Repository
public interface MouseDetailRepository extends JpaRepository<MouseDetail,Integer > {

    @Query("SELECT c FROM MouseDetail c JOIN FETCH c.mouse WHERE c.id=:id")
    public MouseDetail findMouseDetailByIdJoinFetch(Integer id);
}
