package vn.ths.e_commerce.ECommerce.service.serviceInterfaceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ths.e_commerce.ECommerce.entity.Mouse;
import vn.ths.e_commerce.ECommerce.repository.MouseRepository;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.MouseService;

import java.util.List;
import java.util.Optional;

@Service
public class MouseServiceIpm implements MouseService {
    private MouseRepository mouseRepository;

    @Autowired
    public MouseServiceIpm(MouseRepository mouseRepository) {
        this.mouseRepository = mouseRepository;
    }

    @Override
    @Transactional
    public Mouse add(Mouse mouse) {
        return mouseRepository.saveAndFlush(mouse);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        mouseRepository.deleteById(id);
    }

    @Override
    public List<Mouse> getAll() {
        return mouseRepository.findAll();
    }

    @Override
    @Transactional
    public Mouse update(Mouse mouse) {
        Optional<Mouse> optionalProduct = mouseRepository.findById(mouse.getId());
        if(optionalProduct.isPresent()){
            return mouseRepository.saveAndFlush(mouse);
        }
        return null;
    }

    @Override
    public Mouse getById(Integer id) {
        Optional<Mouse> optionalProduct = mouseRepository.findById(id);
        if(optionalProduct.isPresent()){
            return mouseRepository.findById(id).get();
        }
        return null;
    }
}
