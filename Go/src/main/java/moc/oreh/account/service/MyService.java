package moc.oreh.account.service;

import moc.oreh.account.entity.User;
import moc.oreh.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyService {
    @Autowired
    UserRepository userRepository;

    public User getUser() {
        return new User("王美丽", 180);
    }

    @Transactional
    public void editUser() {
        User user = userRepository.get(1);
        user.setWeight(200);
        userRepository.save(user);
    }
}
