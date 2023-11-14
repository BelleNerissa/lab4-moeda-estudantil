package br.com.pucminas.sistemamoedaestudantil.controllers;

import br.com.pucminas.sistemamoedaestudantil.dtos.request.CompraRequestDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.request.MessageDto;
import br.com.pucminas.sistemamoedaestudantil.dtos.response.CompraResponseDTO;
import br.com.pucminas.sistemamoedaestudantil.entities.Aluno;
import br.com.pucminas.sistemamoedaestudantil.services.CompraService;
import br.com.pucminas.sistemamoedaestudantil.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/api/compra")
public class CompraController {

    @Autowired
    private CompraService service;

    @Autowired
    private EmailService emailService;


    @GetMapping(value="/mostrar")
    public ResponseEntity<?> findAllComprasByAlunoId(@RequestParam Integer id){
        try{
            return ResponseEntity.ok().body(service.getByAlunoId(id).stream()
                    .map(CompraResponseDTO::new).collect(Collectors.toList()));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/mostar/vantagem")
    public ResponseEntity<?> findAllComprasByVantagemId(@RequestParam Integer id){
        try{
            return ResponseEntity.ok().body(service.getByVantagemId(id).stream()
                    .map(CompraResponseDTO::new).collect(Collectors.toList()));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value="/cadastrar")
    public ResponseEntity<?> insert(@RequestBody CompraRequestDTO objDTO){
        try{
            // Envie um email para o usuário
            CompraResponseDTO compra = new CompraResponseDTO(service.insert(objDTO));

            MessageDto messageDto = new MessageDto();
            messageDto.setOwner(compra.getAlunoEmail());
            messageDto.setFrom("moedafinanceira2@gmail.com");
            messageDto.setTo(compra.getAlunoEmail());
            messageDto.setTitle("Confirmação de Compra");
            messageDto.setText("Sua compra foi realizada com sucesso. Id da compra: " + compra.getId());

            emailService.sendEmail(messageDto);
            return ResponseEntity.ok().body(compra);
        }catch (Exception e){
            // Adicione logs
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
