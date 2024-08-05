package vn.ths.e_commerce.ECommerce.service.serviceInterface;

import vn.ths.e_commerce.ECommerce.entity.MouseDetail;

import java.util.List;

public interface MouseDetailService {
    public MouseDetail add(MouseDetail mouseDetail);
    public void deleteById(Integer id);
    public List<MouseDetail> getAll();
    public MouseDetail update(MouseDetail mouseDetail);
    public MouseDetail getById(Integer id);
}
