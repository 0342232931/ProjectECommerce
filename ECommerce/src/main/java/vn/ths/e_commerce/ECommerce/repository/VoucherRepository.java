package vn.ths.e_commerce.ECommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.ths.e_commerce.ECommerce.entity.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Integer > {

    @Query("SELECT c FROM Voucher c JOIN FETCH c.user WHERE c.id=:id")
    public Voucher findVoucherByIdJoinFetch(Integer id);
}
