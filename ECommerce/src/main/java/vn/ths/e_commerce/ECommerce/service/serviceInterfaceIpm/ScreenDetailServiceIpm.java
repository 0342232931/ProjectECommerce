package vn.ths.e_commerce.ECommerce.service.serviceInterfaceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ths.e_commerce.ECommerce.entity.ScreenDetail;
import vn.ths.e_commerce.ECommerce.repository.ScreenDetailRepository;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.ScreenDetailService;

import java.util.List;
import java.util.Optional;

@Service
public class ScreenDetailServiceIpm implements ScreenDetailService {
    private final ScreenDetailRepository screenDetailRepository;

    @Autowired
    public ScreenDetailServiceIpm(ScreenDetailRepository screenDetailRepository) {
        this.screenDetailRepository = screenDetailRepository;
    }

    @Override
    @Transactional
    public ScreenDetail add(ScreenDetail screenDetail) {
        return screenDetailRepository.saveAndFlush(screenDetail);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        screenDetailRepository.deleteById(id);
    }

    @Override
    public List<ScreenDetail> getAll() {
        return screenDetailRepository.findAll();
    }

    @Override
    public ScreenDetail getById(Integer id) {
        return screenDetailRepository.findById(id).get();
    }

    @Override
    @Transactional
    public ScreenDetail update(ScreenDetail screenDetail) {
        Optional<ScreenDetail> optionalProductDetail = screenDetailRepository.findById(screenDetail.getId());
        if(optionalProductDetail.isPresent()){
            return screenDetailRepository.saveAndFlush(screenDetail);
        }
        return null;
    }

}
