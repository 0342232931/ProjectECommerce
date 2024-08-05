package vn.ths.e_commerce.ECommerce.service.serviceInterface;

import vn.ths.e_commerce.ECommerce.entity.Mouse;

import java.util.List;

public interface MouseService {
    public Mouse add(Mouse mouse);
    public void deleteById(Integer id);
    public List<Mouse> getAll();
    public Mouse update(Mouse mouse);
    public Mouse getById(Integer id);
}
