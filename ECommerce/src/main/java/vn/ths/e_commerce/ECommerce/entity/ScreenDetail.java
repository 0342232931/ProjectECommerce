package vn.ths.e_commerce.ECommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "screen_detail")
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ScreenDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "kich_thuoc")
    private String kichThuoc;

    @Column(name = "ty_le_khung_hinh")
    private String tyLeKhungHinh;

    @Column(name = "tam_nen")
    private String tamNen;

    @Column(name = "do_sang")
    private String doSang;

    @Column(name = "ty_le_tuong_phan")
    private String tyLeTuongPhan;

    @Column(name = "do_phan_giai")
    private String doPhanGiai;

    @Column(name = "goc_nhin_h_v")
    private String gocNhinHV;

    @Column(name = "tan_so_quet")
    private String tanSoQuet;

    @Column(name = "kha_nang_hien_thi_mau_sac")
    private String khaNangHienThiMauSac;

    @Column(name = "thoi_gian_phan_hoi")
    private String thoiGianPhanHoi;

    @Column(name = "dang_chan_de")
    private String dangChanDe;

    @Column(name = "mau_sac")
    private String mauSac;

    @Column(name = "do_nghieng")
    private String doNghieng;

    @Column(name = "nguon_cap_dien")
    private String nguonCapDien;

    @Column(name = "cong_ket_noi")
    private String congKetNoi;

    @Column(name = "kich_thuoc_rieng_san_pham")
    private String kichThuocRiengSanPham;

    @Column(name = "kich_thuoc_bao_gom_thung_may")
    private String kichThuocBaoGomThungMay;

    @Column(name = "trong_luong_san_pham")
    private String trongLuongSanPham;

    @Column(name = "trong_luong_bao_gom_thung_may")
    private String trongLuongBaoGomThungMay;

    @Column(name = "about")
    private String about;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "screenDetail"
    )
    private Screen screen;
}
