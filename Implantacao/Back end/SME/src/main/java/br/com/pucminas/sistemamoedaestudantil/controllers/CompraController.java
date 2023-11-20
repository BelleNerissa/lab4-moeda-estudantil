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

    @GetMapping(value = "/mostrar")
    public ResponseEntity<?> findAllComprasByAlunoId(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok().body(service.getByAlunoId(id).stream()
                    .map(CompraResponseDTO::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/mostar/vantagem")
    public ResponseEntity<?> findAllComprasByVantagemId(@RequestParam Integer id) {
        try {
            return ResponseEntity.ok().body(service.getByVantagemId(id).stream()
                    .map(CompraResponseDTO::new).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<?> insert(@RequestBody CompraRequestDTO objDTO) {
        try {
            CompraResponseDTO compra = new CompraResponseDTO(service.insert(objDTO));

            // Envie um email para o Aluno
            MessageDto messageDtoAluno = new MessageDto();
            messageDtoAluno.setOwnerAluno(compra.getAlunoEmail());
            messageDtoAluno.setOwnerEmpresa(compra.getEmpresaEmail());
            messageDtoAluno.setFrom("moedafinanceira2@gmail.com");
            messageDtoAluno.setTo(compra.getAlunoEmail());
            messageDtoAluno.setTitle("Confirmação de Compra");
            messageDtoAluno.setText("Sua compra foi realizada com sucesso. Código da compra: " + compra.getId() + "\nEmail Empresa: " + compra.getEmpresaEmail() + "\nVerifique esse código com a empresa responsavel para a retirada de seu produto");
            emailService.sendEmail(messageDtoAluno);

            // Envie um email para a empresa
            MessageDto messageDtoEmpresa = new MessageDto();
            messageDtoEmpresa.setOwnerAluno(compra.getAlunoEmail());
            messageDtoEmpresa.setOwnerEmpresa(compra.getEmpresaEmail());
            messageDtoEmpresa.setFrom("moedafinanceira2@gmail.com");
            messageDtoEmpresa.setTo(compra.getEmpresaEmail());
            messageDtoEmpresa.setTitle("Confirmação de Compra");
            messageDtoEmpresa.setText("Uma compra foi realizada pelo aluno de email: " + compra.getAlunoEmail()
                    + "\nCódigo da compra: " + compra.getId());
            emailService.sendEmail(messageDtoEmpresa);

            return ResponseEntity.ok().body(compra);
        } catch (Exception e) {
            // Adicione logs
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
