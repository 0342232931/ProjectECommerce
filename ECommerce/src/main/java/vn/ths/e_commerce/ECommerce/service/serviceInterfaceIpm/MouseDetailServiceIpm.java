package vn.ths.e_commerce.ECommerce.service.serviceInterfaceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ths.e_commerce.ECommerce.entity.MouseDetail;
import vn.ths.e_commerce.ECommerce.repository.MouseDetailRepository;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.MouseDetailService;

import java.util.List;
import java.util.Optional;

@Service
public class MouseDetailServiceIpm implements MouseDetailService {
    private final MouseDetailRepository mouseDetailRepository;

    @Autowired
    public MouseDetailServiceIpm(MouseDetailRepository mouseDetailRepository) {
        this.mouseDetailRepository = mouseDetailRepository;
    }

    @Override
    @Transactional
    public MouseDetail add(MouseDetail mouseDetail) {
        return mouseDetailRepository.saveAndFlush(mouseDetail);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        mouseDetailRepository.deleteById(id);
    }

    @Override
    public List<MouseDetail> getAll() {
        return mouseDetailRepository.findAll();
    }

    @Override
    public MouseDetail getById(Integer id) {
        return mouseDetailRepository.findById(id).get();
    }

    @Override
    @Transactional
    public MouseDetail update(MouseDetail mouseDetail) {
        Optional<MouseDetail> optionalProductDetail = mouseDetailRepository.findById(mouseDetail.getId());
        if(optionalProductDetail.isPresent()){
            return mouseDetailRepository.saveAndFlush(mouseDetail);
        }
        return null;
    }

}
