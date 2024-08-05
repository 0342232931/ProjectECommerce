package vn.ths.e_commerce.ECommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "keyboard_detail")
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class KeyboardDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "about")
    private String about;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "keyboardDetail"
    )
    private Keyboard keyboard;
}

