package br.com.ecommerce.user.mapper;

import br.com.ecommerce.user.dto.EmailDto;
import br.com.ecommerce.user.entity.Email;

public class EmailMapper {

    public static EmailDto entityToDto(Email email) {
        EmailDto dto = new EmailDto();
        dto.setEmailFrom(email.getEmailFrom());
        dto.setEmailTo(email.getEmailTo());
        dto.setSubject(email.getSubject());
        dto.setText(email.getText());
        dto.setStatusEmail(email.getStatusEmail());
        return dto;
    }

    public static Email dtoToEntity(EmailDto dto) {
        Email email = new Email();
        email.setEmailFrom(dto.getEmailFrom());
        email.setEmailTo(dto.getEmailTo());
        email.setSubject(dto.getSubject());
        email.setText(dto.getText());
        email.setStatusEmail(dto.getStatusEmail());
        return email;
    }

}
