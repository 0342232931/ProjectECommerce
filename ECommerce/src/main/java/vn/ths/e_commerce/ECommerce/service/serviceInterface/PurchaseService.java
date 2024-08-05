package vn.ths.e_commerce.ECommerce.service.serviceInterface;

import vn.ths.e_commerce.ECommerce.entity.Account;
import vn.ths.e_commerce.ECommerce.entity.Purchase;

import java.util.List;

public interface PurchaseService {
    public Purchase add(Purchase purchase);
    public void deleteById(Integer id);
    public List<Purchase> getAll();
    public Purchase update(Purchase purchase);
    public Purchase getById(Integer id);
}
