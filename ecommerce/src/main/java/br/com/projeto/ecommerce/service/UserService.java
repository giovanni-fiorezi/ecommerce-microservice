package br.com.projeto.ecommerce.service;

import br.com.projeto.ecommerce.models.User;
import br.com.projeto.ecommerce.repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "new-user", groupId = "form-new-user")
    public User saveNewUser(User user) {
        userRepository.save(user);
        return user;
    }
}
