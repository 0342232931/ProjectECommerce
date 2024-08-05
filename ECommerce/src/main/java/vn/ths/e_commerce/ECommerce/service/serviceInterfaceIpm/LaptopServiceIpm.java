package vn.ths.e_commerce.ECommerce.service.serviceInterfaceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ths.e_commerce.ECommerce.entity.Laptop;
import vn.ths.e_commerce.ECommerce.repository.LaptopRepository;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.LaptopService;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopServiceIpm implements LaptopService {
    private LaptopRepository laptopRepository;

    @Autowired
    public LaptopServiceIpm(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @Override
    @Transactional
    public Laptop add(Laptop laptop) {
        return laptopRepository.saveAndFlush(laptop);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        laptopRepository.deleteById(id);
    }

    @Override
    public List<Laptop> getAll() {
        return laptopRepository.findAll();
    }

    @Override
    @Transactional
    public Laptop update(Laptop laptop) {
        Optional<Laptop> optionalProduct = laptopRepository.findById(laptop.getId());
        if(optionalProduct.isPresent()){
            return laptopRepository.saveAndFlush(laptop);
        }
        return null;
    }

    @Override
    public Laptop getById(Integer id) {
        Optional<Laptop> optionalProduct = laptopRepository.findById(id);
        if(optionalProduct.isPresent()){
            return laptopRepository.findById(id).get();
        }
        return null;
    }
}
