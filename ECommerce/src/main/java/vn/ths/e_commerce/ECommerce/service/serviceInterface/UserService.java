package vn.ths.e_commerce.ECommerce.service.serviceInterface;

import vn.ths.e_commerce.ECommerce.entity.Account;
import vn.ths.e_commerce.ECommerce.entity.User;

import java.util.List;

public interface UserService {
    public User add(User user);
    public void deleteById(Integer id);
    public List<User> getAll();
    public User update(User user);
    public User getById(Integer id);
}
