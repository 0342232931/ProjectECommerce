package vn.ths.e_commerce.ECommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.ths.e_commerce.ECommerce.entity.Account;
import vn.ths.e_commerce.ECommerce.entity.RoleAccount;
import vn.ths.e_commerce.ECommerce.entity.RoleAccount;

@Repository
public interface RoleAccountRepository extends JpaRepository<RoleAccount,Integer > {
    public RoleAccount findRoleAccountByRoleName(String roleName);

    @Query("SELECT c FROM RoleAccount c JOIN FETCH c.account WHERE c.id=:id")
    public RoleAccount findRoleAccountByIdJoinFetch(Integer id);
}
