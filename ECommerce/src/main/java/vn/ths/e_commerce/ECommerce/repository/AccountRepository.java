package vn.ths.e_commerce.ECommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.ths.e_commerce.ECommerce.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    public Account findByUserName(String userName);

    @Query("SELECT a FROM Account a JOIN FETCH a.roleAccount JOIN FETCH a.user WHERE a.id=:id")
    public Account findAccountByIdJoinFetch(Integer id);
}
