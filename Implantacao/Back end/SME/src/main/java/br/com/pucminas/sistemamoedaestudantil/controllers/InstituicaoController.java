package br.com.pucminas.sistemamoedaestudantil.controllers;
import br.com.pucminas.sistemamoedaestudantil.dtos.request.InstituicaoRequestDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.response.InstituicaoResponseDTO;
import br.com.pucminas.sistemamoedaestudantil.entities.Instituicao;
import br.com.pucminas.sistemamoedaestudantil.services.InstituicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/instituicao")
public class InstituicaoController {
    @Autowired
    private InstituicaoService service;

    @GetMapping(value = "/listar")
    public ResponseEntity listar(){
        List<InstituicaoResponseDTO> list = service.findAll();
        if (list.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhuma instituição encontrada.");
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/mostrar/id/{id}")
    public ResponseEntity<InstituicaoResponseDTO> show(@PathVariable Integer id) throws Exception {
        InstituicaoResponseDTO obj = new InstituicaoResponseDTO(service.getById(id));
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity insert (@RequestBody InstituicaoRequestDTO dto) {
        ResponseEntity resp = service.insert(dto);
        return resp;
    }

    @DeleteMapping  (value = "/deletar/id/{id}")
    public void  delete (@PathVariable Integer id) throws Exception {
        service.deleteById(id);
    }

    @PutMapping  (value = "/update/id/{id}")
    public ResponseEntity<Instituicao>  delete (@PathVariable Integer id, @RequestBody Instituicao obj ) throws Exception {
        Instituicao instituicaoAtualizado = service.update(id,obj);
        return ResponseEntity.ok().body(instituicaoAtualizado);

    }
}
