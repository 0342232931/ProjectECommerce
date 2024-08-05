package vn.ths.e_commerce.ECommerce.service.serviceInterface;

import vn.ths.e_commerce.ECommerce.entity.LaptopDetail;

import java.util.List;

public interface LaptopDetailService {
    public LaptopDetail add(LaptopDetail laptopDetail);
    public void deleteById(Integer id);
    public List<LaptopDetail> getAll();
    public LaptopDetail update(LaptopDetail laptopDetail);
    public LaptopDetail getById(Integer id);
}
