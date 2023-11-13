package br.com.pucminas.sistemamoedaestudantil.controllers;

import br.com.pucminas.sistemamoedaestudantil.dtos.request.TransacaoRequestDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.response.TransacaoResponseDTO;
import br.com.pucminas.sistemamoedaestudantil.entities.Transacao;
import br.com.pucminas.sistemamoedaestudantil.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/api/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService service;


// TODO: Listar transacoes pelo Id do aluno/professor

    /**
     * Método lida com solicitações GET.
     * */
    @GetMapping(value="/listar/professor")
    public ResponseEntity<?> findAllTransactionByProfessorId(@RequestParam("id") Integer id){
        List<TransacaoResponseDTO> list = service.findAllTransactionByProfessorId(id);
        if(list.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhuma transação encontrada para este professor");
        return ResponseEntity.ok().body(list);
    }

    /**
     * Método lida com solicitações GET.
     * @param id id do aluno.
     * */
    @GetMapping(value="/listar/aluno")
    public ResponseEntity<?> findAllTransactionByAlunoId(@RequestParam("id") Integer id){
        List<TransacaoResponseDTO> list = service.findAllTransactionByAlunoId(id);
        if(list.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhuma transação encontrada para este aluno");
        return ResponseEntity.ok().body(list);
    }

    /**
     * Método lida com solicitações POST.
     * @param dto dto do professor.
     * */
    @PostMapping(value = "/cadastrar/byprofessor")
    public ResponseEntity<?> insertByProfessor (@RequestBody TransacaoRequestDTO dto) throws Exception {
        ResponseEntity resp = service.insertByProfessor(dto);
        return resp;
    }

    /**
     * Método lida com solicitações POST.
     * @param dto dto do aluno.
     * */
    @PostMapping(value = "/cadastrar/byaluno")
    public ResponseEntity<?> insertByAluno (@RequestBody TransacaoRequestDTO dto) throws Exception {
        ResponseEntity resp = service.insertByAluno(dto);
        return resp;
    }
}
