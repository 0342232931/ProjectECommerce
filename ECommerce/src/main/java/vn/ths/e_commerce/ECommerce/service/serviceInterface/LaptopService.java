package vn.ths.e_commerce.ECommerce.service.serviceInterface;

import vn.ths.e_commerce.ECommerce.entity.Laptop;

import java.util.List;

public interface LaptopService {
    public Laptop add(Laptop laptop);
    public void deleteById(Integer id);
    public List<Laptop> getAll();
    public Laptop update(Laptop laptop);
    public Laptop getById(Integer id);
}
