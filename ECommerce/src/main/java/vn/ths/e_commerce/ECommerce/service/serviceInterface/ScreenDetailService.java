package vn.ths.e_commerce.ECommerce.service.serviceInterface;

import vn.ths.e_commerce.ECommerce.entity.ScreenDetail;

import java.util.List;

public interface ScreenDetailService {
    public ScreenDetail add(ScreenDetail screenDetail);
    public void deleteById(Integer id);
    public List<ScreenDetail> getAll();
    public ScreenDetail update(ScreenDetail screenDetail);
    public ScreenDetail getById(Integer id);
}
