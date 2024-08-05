package vn.ths.e_commerce.ECommerce.service.serviceInterfaceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ths.e_commerce.ECommerce.entity.User;
import vn.ths.e_commerce.ECommerce.repository.UserRepository;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceIpm implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceIpm(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User add(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User update(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isPresent()){
            return userRepository.saveAndFlush(user);
        }
        return null;
    }

    @Override
    public User getById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return userRepository.findById(id).get();
        }
        return null;
    }
}
