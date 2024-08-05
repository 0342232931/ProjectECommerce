package vn.ths.e_commerce.ECommerce.service.serviceInterfaceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ths.e_commerce.ECommerce.entity.Keyboard;
import vn.ths.e_commerce.ECommerce.repository.KeyboardRepository;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.KeyboardService;

import java.util.List;
import java.util.Optional;

@Service
public class KeyboardServiceIpm implements KeyboardService {
    private KeyboardRepository keyboardRepository;

    @Autowired
    public KeyboardServiceIpm(KeyboardRepository keyboardRepository) {
        this.keyboardRepository = keyboardRepository;
    }

    @Override
    @Transactional
    public Keyboard add(Keyboard keyboard) {
        return keyboardRepository.saveAndFlush(keyboard);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        keyboardRepository.deleteById(id);
    }

    @Override
    public List<Keyboard> getAll() {
        return keyboardRepository.findAll();
    }

    @Override
    @Transactional
    public Keyboard update(Keyboard keyboard) {
        Optional<Keyboard> optionalProduct = keyboardRepository.findById(keyboard.getId());
        if(optionalProduct.isPresent()){
            return keyboardRepository.saveAndFlush(keyboard);
        }
        return null;
    }

    @Override
    public Keyboard getById(Integer id) {
        Optional<Keyboard> optionalProduct = keyboardRepository.findById(id);
        if(optionalProduct.isPresent()){
            return keyboardRepository.findById(id).get();
        }
        return null;
    }
}
