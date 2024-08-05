package vn.ths.e_commerce.ECommerce.service.serviceInterface;

import vn.ths.e_commerce.ECommerce.entity.Keyboard;

import java.util.List;

public interface KeyboardService {
    public Keyboard add(Keyboard keyboard);
    public void deleteById(Integer id);
    public List<Keyboard> getAll();
    public Keyboard update(Keyboard keyboard);
    public Keyboard getById(Integer id);
}
