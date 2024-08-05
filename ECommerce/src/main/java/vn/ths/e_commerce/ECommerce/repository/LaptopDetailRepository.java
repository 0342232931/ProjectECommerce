package vn.ths.e_commerce.ECommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.ths.e_commerce.ECommerce.entity.LaptopDetail;

@Repository
public interface LaptopDetailRepository extends JpaRepository<LaptopDetail,Integer > {

    @Query("SELECT c FROM LaptopDetail c JOIN FETCH c.laptop WHERE c.id=:id")
    public LaptopDetail findLaptopDetailByIdJoinFetch(Integer id);
}
