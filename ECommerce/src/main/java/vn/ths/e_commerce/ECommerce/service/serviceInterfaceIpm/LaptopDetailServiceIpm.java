package vn.ths.e_commerce.ECommerce.service.serviceInterfaceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ths.e_commerce.ECommerce.entity.LaptopDetail;
import vn.ths.e_commerce.ECommerce.repository.LaptopDetailRepository;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.LaptopDetailService;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopDetailServiceIpm implements LaptopDetailService {
    private final LaptopDetailRepository laptopDetailRepository;

    @Autowired
    public LaptopDetailServiceIpm(LaptopDetailRepository laptopDetailRepository) {
        this.laptopDetailRepository = laptopDetailRepository;
    }

    @Override
    @Transactional
    public LaptopDetail add(LaptopDetail laptopDetail) {
        return laptopDetailRepository.saveAndFlush(laptopDetail);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        laptopDetailRepository.deleteById(id);
    }

    @Override
    public List<LaptopDetail> getAll() {
        return laptopDetailRepository.findAll();
    }

    @Override
    public LaptopDetail getById(Integer id) {
        return laptopDetailRepository.findById(id).get();
    }

    @Override
    @Transactional
    public LaptopDetail update(LaptopDetail laptopDetail) {
        Optional<LaptopDetail> optionalProductDetail = laptopDetailRepository.findById(laptopDetail.getId());
        if(optionalProductDetail.isPresent()){
            return laptopDetailRepository.saveAndFlush(laptopDetail);
        }
        return null;
    }

}
