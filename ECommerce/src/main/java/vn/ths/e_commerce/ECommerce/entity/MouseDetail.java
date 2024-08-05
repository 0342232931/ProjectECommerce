package vn.ths.e_commerce.ECommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mouse_detail")
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MouseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "content")
    private String content;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "mouseDetail"
    )
    private Mouse mouse;
}

