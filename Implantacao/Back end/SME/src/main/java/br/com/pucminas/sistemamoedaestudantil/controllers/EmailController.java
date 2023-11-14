package br.com.pucminas.sistemamoedaestudantil.controllers;
import br.com.pucminas.sistemamoedaestudantil.dtos.request.MessageDto;
import br.com.pucminas.sistemamoedaestudantil.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {
    private final EmailService service;
    @PostMapping("/send")
    public ResponseEntity<MessageDto> sendEmail(@Valid @RequestBody MessageDto messageDto) {
        service.sendEmail(messageDto);
        return ResponseEntity.ok(messageDto);
    }
}
