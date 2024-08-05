package vn.ths.e_commerce.ECommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;
import java.util.List;

@Entity
@Table(name = "screen")
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor

public class Screen {
         @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(name = "screen_name")
        private String screenName;

        @Column(name = "title")
        private String title;

        @Column(name = "type")
        private String type;

        @Column(name = "price")
        private Double price;

        @Column(name = "discount")
        private Double discount;

        @Column(name = "image")
        @Lob
        private Blob image;

        @OneToOne(fetch = FetchType.LAZY,
                cascade = CascadeType.ALL
        )
        @JoinColumn(name = "screen_detail_id")
        private ScreenDetail screenDetail;

        @ManyToMany(fetch = FetchType.LAZY,
                cascade = {
                        CascadeType.DETACH, CascadeType.MERGE,
                        CascadeType.PERSIST, CascadeType.REFRESH
                }
        )
        @JoinTable(name = "cart_screen",
                joinColumns = @JoinColumn(name = "screen_id"),
                inverseJoinColumns = @JoinColumn(name = "cart_id")
        )
        private List<Cart> cart;

        @ManyToMany(fetch = FetchType.LAZY,
                cascade = {
                        CascadeType.DETACH, CascadeType.MERGE,
                        CascadeType.PERSIST, CascadeType.REFRESH
                }
        )
        @JoinTable(name = "purchase_screen",
                joinColumns = @JoinColumn(name = "screen_id"),
                inverseJoinColumns = @JoinColumn(name = "purchase_id")
        )
        private List<Purchase> purchase;

}
