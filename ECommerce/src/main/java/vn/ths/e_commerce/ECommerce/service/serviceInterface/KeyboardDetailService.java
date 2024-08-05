package vn.ths.e_commerce.ECommerce.service.serviceInterface;

import vn.ths.e_commerce.ECommerce.entity.KeyboardDetail;

import java.util.List;

public interface KeyboardDetailService {
    public KeyboardDetail add(KeyboardDetail keyboardDetail);
    public void deleteById(Integer id);
    public List<KeyboardDetail> getAll();
    public KeyboardDetail update(KeyboardDetail keyboardDetail);
    public KeyboardDetail getById(Integer id);
}
