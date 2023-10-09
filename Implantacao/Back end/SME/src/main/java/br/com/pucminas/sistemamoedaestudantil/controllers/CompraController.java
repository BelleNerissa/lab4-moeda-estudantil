package br.com.pucminas.sistemamoedaestudantil.controllers;

import br.com.pucminas.sistemamoedaestudantil.dtos.request.CompraRequestDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.response.CompraResponseDTO;
import br.com.pucminas.sistemamoedaestudantil.services.CompraService;
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
            return ResponseEntity.ok().body(new CompraResponseDTO(service.insert(objDTO)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
