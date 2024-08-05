package vn.ths.e_commerce.ECommerce.service.serviceInterfaceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ths.e_commerce.ECommerce.entity.Screen;
import vn.ths.e_commerce.ECommerce.repository.ScreenRepository;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.ScreenService;

import java.util.List;
import java.util.Optional;

@Service
public class ScreenServiceIpm implements ScreenService {
    private ScreenRepository screenRepository;

    @Autowired
    public ScreenServiceIpm(ScreenRepository screenRepository) {
        this.screenRepository = screenRepository;
    }

    @Override
    @Transactional
    public Screen add(Screen screen) {
        return screenRepository.saveAndFlush(screen);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        screenRepository.deleteById(id);
    }

    @Override
    public List<Screen> getAll() {
        return screenRepository.findAll();
    }

    @Override
    @Transactional
    public Screen update(Screen screen) {
        Optional<Screen> optionalProduct = screenRepository.findById(screen.getId());
        if(optionalProduct.isPresent()){
            return screenRepository.saveAndFlush(screen);
        }
        return null;
    }

    @Override
    public Screen getById(Integer id) {
        Optional<Screen> optionalProduct = screenRepository.findById(id);
        if(optionalProduct.isPresent()){
            return screenRepository.findById(id).get();
        }
        return null;
    }
}
