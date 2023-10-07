package br.com.pucminas.sistemamoedaestudantil.services;

import br.com.pucminas.sistemamoedaestudantil.dtos.request.AlunoRequestDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.response.AlunoResponseDTO;
import br.com.pucminas.sistemamoedaestudantil.entities.Aluno;
import br.com.pucminas.sistemamoedaestudantil.repositories.AlunoReposiory;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoReposiory repository;

    @Transactional
    public List<AlunoResponseDTO> findAll(){
        List<Aluno> listResponse = repository.findAll();
        return listResponse.stream().map(obj -> new AlunoResponseDTO(obj)).collect(Collectors.toList());
    }

    @Transactional
    public Aluno getById(Integer id) throws Exception {
        try{
        Optional<Aluno> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(1,
                "Aluno não encontrado.\n Id: " + id));
        }catch(Exception e){
            throw new Exception("Aluno não encontrado");
        }
    }

    @Transactional
    public ResponseEntity<AlunoRequestDTO> insert(AlunoRequestDTO objDTO){
        Aluno obj = repository.save(objDTO.build());
        return ResponseEntity.ok().body(new AlunoRequestDTO(obj));
    }

    @Transactional
    public void deleteById(Integer id) throws Exception {
        getById(id);
        try{
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Não foi possivel excluir este aluno.");
        }
    }

    public Aluno update(Integer id, Aluno obj) throws Exception {
       Aluno newAluno = getById(id);
       newAluno.setCpf(obj.getCpf());
       newAluno.setEmail(obj.getEmail());
       newAluno.setEndereco(obj.getEndereco());
       newAluno.setNome(obj.getNome());
       newAluno.setRg(obj.getRg());
       //newAluno.setSenha(obj.getSenha());
       newAluno.setSaldo(obj.getSaldo());
       return repository.save(newAluno);
    }
    
}
