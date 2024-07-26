package br.com.ecommerce.user.service;

import br.com.ecommerce.user.entity.Email;
import br.com.ecommerce.user.entity.User;
import br.com.ecommerce.user.enums.StatusEmail;
import br.com.ecommerce.user.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EmailService {

    final EmailRepository emailRepository;
    final JavaMailSender javaMailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender javaMailSender) {
        this.emailRepository = emailRepository;
        this.javaMailSender = javaMailSender;
    }

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public Email sendEmailNewUser(Email email, User user) {
        try {

            String userName = user.getFullName();
            String login = user.getLogin();
            String senha = user.getSenha();

            String subject = "Boas vindas na plataforma E_Tech";
            String messageText = String.format("Seja Bem-Vindo a nossa plataforma, %s! " +
                    "\n" +
                    "Obrigado por se cadastrar e que tenha uma ótima experiência! " +
                    "\n" +
                    "Login: %s \n" +
                    "Senha: %s", userName, login, senha);

            email.setSendDateEmail(LocalDateTime.now());
            email.setEmailFrom(emailFrom);
            email.setUser(user);
            email.setSubject(subject);
            email.setText(messageText);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getEmailTo());
            message.setSubject(subject); //Todo -> após estar funcionando, não deixar nada hard-code, extrair para uma constante
            message.setText(messageText);

            javaMailSender.send(message);
            email.setStatusEmail(StatusEmail.ENVIADO);
            return emailRepository.save(email);
        } catch (MailException e) {
            e.printStackTrace();
            email.setStatusEmail(StatusEmail.ERRO);
            emailRepository.save(email);
            throw new RuntimeException("Erro ao enviar o email para novo usuário, tente novamente!");
        }
    }
}
