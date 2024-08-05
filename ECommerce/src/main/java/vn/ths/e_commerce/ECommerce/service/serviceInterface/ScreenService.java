package vn.ths.e_commerce.ECommerce.service.serviceInterface;

import vn.ths.e_commerce.ECommerce.entity.Screen;

import java.util.List;

public interface ScreenService {
    public Screen add(Screen screen);
    public void deleteById(Integer id);
    public List<Screen> getAll();
    public Screen update(Screen screen);
    public Screen getById(Integer id);
}
