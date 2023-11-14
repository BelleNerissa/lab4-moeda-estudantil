package br.com.pucminas.sistemamoedaestudantil.services;

import br.com.pucminas.sistemamoedaestudantil.dtos.request.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender sender;

    public void sendEmail(MessageDto messageDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(messageDto.getFrom());
        message.setTo(messageDto.getTo());
        message.setSubject(messageDto.getTitle());
        message.setText(messageDto.getText());

        sender.send(message);
    }
}
