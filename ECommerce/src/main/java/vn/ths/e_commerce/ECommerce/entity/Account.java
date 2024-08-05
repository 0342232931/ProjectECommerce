package vn.ths.e_commerce.ECommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "account")
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Phần Này Không Được Để Trống")
    @Size(min = 5,message = "Độ Dài Tối Thiểu Là 5 Kí Tự")
    @Column(name = "username")
    private String userName;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[@!#$%^&+=])(?=\\S+$).*$",
            message = "Mật khẩu phải chứa ít nhất 1 chữ số và 1 ký tự đặc biệt")
    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "role_id")
    private RoleAccount roleAccount;

    @OneToOne(fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
