package vn.ths.e_commerce.ECommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.ths.e_commerce.ECommerce.entity.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Integer > {

    @Query("SELECT c FROM Purchase c JOIN FETCH c.laptop JOIN FETCH c.mouses " +
            "JOIN FETCH c.screens JOIN FETCH c.keyboards JOIN FETCH c.user WHERE c.id=:id")
    public Purchase findPurchaseByIdJoinFetch(Integer id);
}
