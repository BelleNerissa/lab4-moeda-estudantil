package br.com.pucminas.sistemamoedaestudantil.controllers;

import br.com.pucminas.sistemamoedaestudantil.dtos.request.EmpresaRequestDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.response.EmpresaResponseDTO;
import br.com.pucminas.sistemamoedaestudantil.entities.Empresa;
import br.com.pucminas.sistemamoedaestudantil.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/empresa")
public class EmpresaController {

    @Autowired
    EmpresaService service;

    /**
     * Método lida com solicitações GET
     * */
    @GetMapping(value = "/listar")
    public ResponseEntity listar(){
        List<EmpresaResponseDTO> list = service.findAll();
        if (list.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum colaborador encontrado.");
        return ResponseEntity.ok().body(list);
    }

    /**
     * Método lida com solicitações GET
     * @param id id da empresa.
     * */
    @GetMapping(value = "/mostrar/id/{id}")
    public ResponseEntity<Empresa> show(@PathVariable Integer id) {
        Empresa obj = service.getById(id);
        return ResponseEntity.ok().body(obj);
    }

    /**
     * Método lida com solicitações POST
     * @param dto dto da empresa.
     * */
    @PostMapping(value = "/cadastrar")
    public ResponseEntity insert (@RequestBody EmpresaRequestDTO dto) {
        ResponseEntity resp = service.insert(dto);
        return resp;
    }

    /**
     * Método lida com solicitações DELETE
     * @param id id da empresaa.
     * */
    @DeleteMapping(value = "/deletar/id/{id}")
    public void  delete (@PathVariable Integer id) {
        service.deleteById(id);
    }

    /**
     * Método lida com solicitações PUT
     * @param id id da empresa.
     * @param obj objeto da empresa.
     * */
    @PutMapping  (value = "/update/id/{id}")
    public ResponseEntity<Empresa>  delete (@PathVariable Integer id,@RequestBody Empresa obj ) throws Exception {
        Empresa empresaAtualizada = service.update(id,obj);
       return ResponseEntity.ok().body(empresaAtualizada);
        
    }

}
