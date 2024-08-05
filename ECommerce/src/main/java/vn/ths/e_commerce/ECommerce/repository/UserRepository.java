package vn.ths.e_commerce.ECommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.ths.e_commerce.ECommerce.entity.Account;
import vn.ths.e_commerce.ECommerce.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer > {
    public User findUserByAccount(Account account);
    @Query("SELECT c FROM User c JOIN FETCH c.cart JOIN FETCH c.voucher JOIN FETCH c.purchase JOIN FETCH c.account WHERE c.id=:id")
    public User findUserByIdJoinFetch(Integer id);
}
