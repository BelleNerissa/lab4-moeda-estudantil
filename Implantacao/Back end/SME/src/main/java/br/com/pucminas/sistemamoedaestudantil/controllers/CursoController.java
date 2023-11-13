package br.com.pucminas.sistemamoedaestudantil.controllers;

import br.com.pucminas.sistemamoedaestudantil.dtos.request.CursoRequestDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.response.CursoResponseDTO;
import br.com.pucminas.sistemamoedaestudantil.entities.Curso;
import br.com.pucminas.sistemamoedaestudantil.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/curso")
public class CursoController {
    @Autowired
    private CursoService service;

    /**
     * Método lida com solicitações GET.
     * */
    @GetMapping(value = "/listar")
    public ResponseEntity listar(){
        List<CursoResponseDTO> list = service.findAll();
        if (list.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum curso encontrado.");
        return ResponseEntity.ok().body(list);
    }

    /**
     * Método lida com solicitações GET
     * @param id do curso.
     * */
    @GetMapping(value = "/mostrar/id/{id}")
    public ResponseEntity<CursoResponseDTO> show(@PathVariable Integer id) throws Exception {
        CursoResponseDTO obj = new CursoResponseDTO(service.getById(id));
        return ResponseEntity.ok().body(obj);
    }

    /**
     * Método lida com solicitações POST
     * @param dto dto do curso.
     * */
    @PostMapping(value = "/cadastrar")
    public ResponseEntity insert (@RequestBody CursoRequestDTO dto) {
        ResponseEntity resp = service.insert(dto);
        return resp;
    }

    /**
     * Método lida com solicitações DELETE
     * @param id id do curso.
     * */
    @DeleteMapping  (value = "/deletar/id/{id}")
    public void  delete (@PathVariable Integer id) throws Exception {
        service.deleteById(id);
    }

    /**
     *
     * @param id
     * @param obj
     * */
    @PutMapping  (value = "/update/id/{id}")
    public ResponseEntity<Curso>  delete (@PathVariable Integer id, @RequestBody Curso obj ) throws Exception {
        Curso cursoAtualizado = service.update(id,obj);
        return ResponseEntity.ok().body(cursoAtualizado);

    }
}
