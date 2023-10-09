package br.com.pucminas.sistemamoedaestudantil.controllers;


import br.com.pucminas.sistemamoedaestudantil.dtos.request.ProfessorRequestDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.response.ProfessorResponseDTO;
import br.com.pucminas.sistemamoedaestudantil.entities.Professor;
import br.com.pucminas.sistemamoedaestudantil.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/professor")
public class ProfessorController {
    @Autowired
    private ProfessorService service;

    @GetMapping(value = "/listar")
    public ResponseEntity listar(){
        List<ProfessorResponseDTO> list = service.findAll();
        if (list.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum professor encontrado.");
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/mostrar/id/{id}")
    public ResponseEntity<ProfessorResponseDTO> show(@PathVariable Integer id) throws Exception {
        ProfessorResponseDTO obj = new ProfessorResponseDTO(service.getById(id));
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity insert (@RequestBody ProfessorRequestDTO dto) {
        ResponseEntity resp = service.insert(dto);
        return resp;
    }

    @DeleteMapping  (value = "/deletar/id/{id}")
    public void  delete (@PathVariable Integer id) throws Exception {
        service.deleteById(id);
    }

    @PutMapping  (value = "/update/id/{id}")
    public ResponseEntity<Professor>  delete (@PathVariable Integer id, @RequestBody Professor obj ) throws Exception {
        Professor professorAtualizado = service.update(id,obj);
        return ResponseEntity.ok().body(professorAtualizado);

    }
}
