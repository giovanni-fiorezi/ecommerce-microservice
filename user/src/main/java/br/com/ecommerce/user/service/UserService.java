package br.com.ecommerce.user.service;

import br.com.ecommerce.user.dto.UserDto;
import br.com.ecommerce.user.entity.Email;
import br.com.ecommerce.user.entity.User;
import br.com.ecommerce.user.mapper.UserMapper;
import br.com.ecommerce.user.repository.UserRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final String REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public UserService(UserRepository userRepository, EmailService emailService, KafkaTemplate<String, Object> kafkaTemplate) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.kafkaTemplate = kafkaTemplate;
    }

    public List<UserDto> findAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return users.stream()
                    .map(UserMapper::entityToDto)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public UserDto findById(UUID userId) {
        try {
            if(userId == null) {
                throw new RuntimeException("Id vazio, passe um id para buscar o usuário");
            }
            Optional<User> user = userRepository.findById(userId);
            return UserMapper.entityToDto(user.get());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Problema ao buscar o usuário! Tente novamente.");
        }
    }

    public UserDto findByLogin(String login) {
        try {
            if(login.isEmpty() || login.isBlank()) {
                throw new RuntimeException("Login vazio, passe um login para buscar o usuário");
            }
            Optional<User> user = userRepository.findByLogin(login);
            return UserMapper.entityToDto(user.get());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Problema ao buscar o usuário! Tente novamente.");
        }
    }

    /* Sempre que um novo usuário for cadastrado será enviado um email de Boas-Vindas
    * e será enviado para o tópico do kafka esse usuário */
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(User user) {
        try {
            if(user == null) {
                throw new RuntimeException("Insira os dados do usuário!");
            }
            String emailUser = user.getEmail();
            boolean emailValido = emailUser.matches(REGEX_EMAIL);

            if(emailValido) {
                userRepository.save(user);
            }
            Email email = new Email();
            email.setEmailTo(user.getEmail());

            emailService.sendEmailNewUser(email, user);

            CompletableFuture<SendResult<String, Object>> send = kafkaTemplate.send("new-user", user);
            System.out.println(send);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor=Exception.class)
    public void removeUser(UUID id) {
        if(id == null) {
            throw new RuntimeException("Insira um id válido!");
        }
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.deleteById(id);
        }
    }
}
