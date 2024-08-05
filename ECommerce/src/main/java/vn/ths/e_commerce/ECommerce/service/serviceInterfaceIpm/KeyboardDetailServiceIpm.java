package vn.ths.e_commerce.ECommerce.service.serviceInterfaceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ths.e_commerce.ECommerce.entity.KeyboardDetail;
import vn.ths.e_commerce.ECommerce.repository.KeyboardDetailRepository;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.KeyboardDetailService;

import java.util.List;
import java.util.Optional;

@Service
public class KeyboardDetailServiceIpm implements KeyboardDetailService {
    private final KeyboardDetailRepository keyboardDetailRepository;

    @Autowired
    public KeyboardDetailServiceIpm(KeyboardDetailRepository keyboardDetailRepository) {
        this.keyboardDetailRepository = keyboardDetailRepository;
    }

    @Override
    @Transactional
    public KeyboardDetail add(KeyboardDetail keyboardDetail) {
        return keyboardDetailRepository.saveAndFlush(keyboardDetail);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        keyboardDetailRepository.deleteById(id);
    }

    @Override
    public List<KeyboardDetail> getAll() {
        return keyboardDetailRepository.findAll();
    }

    @Override
    public KeyboardDetail getById(Integer id) {
        return keyboardDetailRepository.findById(id).get();
    }

    @Override
    @Transactional
    public KeyboardDetail update(KeyboardDetail keyboardDetail) {
        Optional<KeyboardDetail> optionalProductDetail = keyboardDetailRepository.findById(keyboardDetail.getId());
        if(optionalProductDetail.isPresent()){
            return keyboardDetailRepository.saveAndFlush(keyboardDetail);
        }
        return null;
    }

}
