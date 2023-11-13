package br.com.pucminas.sistemamoedaestudantil.controllers;

import br.com.pucminas.sistemamoedaestudantil.dtos.request.LoginRequestDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.response.AlunoResponseDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.response.EmpresaResponseDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.response.ProfessorResponseDTO;
import br.com.pucminas.sistemamoedaestudantil.entities.Aluno;
import br.com.pucminas.sistemamoedaestudantil.entities.Empresa;
import br.com.pucminas.sistemamoedaestudantil.entities.Professor;
import br.com.pucminas.sistemamoedaestudantil.entities.Usuario;
import br.com.pucminas.sistemamoedaestudantil.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * Método lida com solicitações POST.
     * @param dto dto do login.
     * */
    @PostMapping
    public ResponseEntity<?> loginRequest(@RequestBody LoginRequestDTO dto){
        Usuario user;
        try{
            user = loginService.validarLogin(dto.getEmail(), dto.getSenha());
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
        return ResponseEntity.ok().body(user);
    }


//    @PostMapping(value = "/aluno")
//    public ResponseEntity<?> loginAlunoRequest(@RequestBody LoginRequestDTO dto){
//        Aluno aluno;
//        try{
//            aluno = loginService.validateAlunoLogin(dto.getEmail(), dto.getSenha());
//        }catch(IllegalArgumentException e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//        return ResponseEntity.ok().body(new AlunoResponseDTO(aluno));
//    }
//
//    @PostMapping(value = "/professor")
//    public ResponseEntity<?> loginProfessorRequest(@RequestBody LoginRequestDTO dto){
//        Professor obj;
//        try{
//            obj = loginService.validateProfessorLogin(dto.getEmail(), dto.getSenha());
//        }catch(IllegalArgumentException e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//        return ResponseEntity.ok().body(new ProfessorResponseDTO(obj));
//    }
//
//    @PostMapping(value = "/empresa")
//    public ResponseEntity<?> loginEmpresaRequest(@RequestBody LoginRequestDTO dto){
//        Empresa obj;
//        try{
//            obj = loginService.validateEmpresaLogin(dto.getEmail(), dto.getSenha());
//        }catch(IllegalArgumentException e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//        return ResponseEntity.ok().body(new EmpresaResponseDTO(obj));
//    }
}