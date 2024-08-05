package vn.ths.e_commerce.ECommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "role_account")
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoleAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {
            CascadeType.DETACH,CascadeType.PERSIST,
            CascadeType.REFRESH,CascadeType.MERGE
            }, mappedBy = "roleAccount"
    )
    private List<Account> account;
}
