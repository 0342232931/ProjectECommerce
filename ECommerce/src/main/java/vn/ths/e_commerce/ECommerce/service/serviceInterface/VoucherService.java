package vn.ths.e_commerce.ECommerce.service.serviceInterface;

import vn.ths.e_commerce.ECommerce.entity.Voucher;

import java.util.List;

public interface VoucherService {
    public Voucher add(Voucher voucher);
    public void deleteById(Integer id);
    public List<Voucher> getAll();
    public Voucher update(Voucher voucher);
    public Voucher getById(Integer id);
}
