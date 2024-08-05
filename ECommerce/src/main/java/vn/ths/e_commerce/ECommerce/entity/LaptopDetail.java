package vn.ths.e_commerce.ECommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "laptop_detail")
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class LaptopDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "cpu")
    private String cpu;

    @Column(name = "ram")
    private String ram;

    @Column(name = "o_cung")
    private String oCung;

    @Column(name =  "card_do_hoa")
    private String cardDoHoa;

    @Column(name = "man_hinh")
    private String manHinh;

    @Column(name = "cong_giao_tiep")
    private String congGiaoTiep;

    @Column(name = "audio")
    private String audio;

    @Column(name = "chuan_lan")
    private String chuanLan;

    @Column(name = "chuan_wifi")
    private String chuanWifi;

    @Column(name = "bluetooth")
    private String bluetooth;

    @Column(name = "web_cam")
    private String webCam;

    @Column(name = "he_dieu_hanh")
    private String heDieuHanh;

    @Column(name = "pin")
    private String pin;

    @Column(name = "trong_luong")
    private String trongLuong;

    @Column(name = "mau_sac")
    private String mauSac;

    @Column(name = "kich_thuoc")
    private String kichThuoc;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY,
           cascade = CascadeType.ALL,
            mappedBy = "laptopDetail"
    )
    private Laptop laptop;
}
