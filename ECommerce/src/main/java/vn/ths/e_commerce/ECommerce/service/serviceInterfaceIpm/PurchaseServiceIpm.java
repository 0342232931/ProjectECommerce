package vn.ths.e_commerce.ECommerce.service.serviceInterfaceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ths.e_commerce.ECommerce.entity.Purchase;
import vn.ths.e_commerce.ECommerce.repository.PurchaseRepository;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.PurchaseService;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceIpm implements PurchaseService {
    private PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceIpm(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    @Transactional
    public Purchase add(Purchase purchase) {
        return purchaseRepository.saveAndFlush(purchase);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        purchaseRepository.deleteById(id);
    }

    @Override
    public List<Purchase> getAll() {
        return purchaseRepository.findAll();
    }

    @Override
    @Transactional
    public Purchase update(Purchase purchase) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(purchase.getId());
        if(optionalPurchase.isPresent()){
            return purchaseRepository.saveAndFlush(purchase);
        }
        return null;
    }

    @Override
    public Purchase getById(Integer id) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(id);
        if(optionalPurchase.isPresent()){
            return purchaseRepository.findById(id).get();
        }
        return null;
    }
}
