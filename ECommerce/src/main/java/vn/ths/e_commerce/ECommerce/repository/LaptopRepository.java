package vn.ths.e_commerce.ECommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.ths.e_commerce.ECommerce.entity.Laptop;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop,Integer > {

    @Query("SELECT c FROM Laptop c JOIN FETCH c.purchase JOIN FETCH c.cart JOIN FETCH c.laptopDetail WHERE c.id=:id")
    public Laptop findLaptopByIdJoinFetch(Integer id);
}
