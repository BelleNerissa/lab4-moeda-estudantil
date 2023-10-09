package br.com.pucminas.sistemamoedaestudantil.services;

import br.com.pucminas.sistemamoedaestudantil.Exception.EmailJaCadastradoException;
import br.com.pucminas.sistemamoedaestudantil.dtos.request.AlunoRequestDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.response.AlunoResponseDTO;
import br.com.pucminas.sistemamoedaestudantil.entities.Aluno;
import br.com.pucminas.sistemamoedaestudantil.entities.Professor;
import br.com.pucminas.sistemamoedaestudantil.repositories.AlunoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.InvalidTransactionException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Transactional
    public List<AlunoResponseDTO> findAll(){
        List<Aluno> listResponse = repository.findAll();
        return listResponse.stream().map(AlunoResponseDTO::new).collect(Collectors.toList());
    }

    public void save(Aluno aluno){
        repository.save(aluno);
    }

    public void subtrairMoedas(double valor, Integer id) throws Exception {
        Aluno aluno = getById(id);
        if(aluno.getSaldo() - valor >= 0){
            aluno.setSaldo(aluno.getSaldo() - valor);
            repository.save(aluno);
        }else
            throw new InvalidTransactionException("Não foi possivel realizar a transacao, verifique a quantidade transferida");
    }

    @Transactional
    public void adicionarMoedas(double valor, Integer id) throws Exception {
        Aluno aluno = getById(id);
        aluno.setSaldo(aluno.getSaldo() + valor);
        repository.save(aluno);
    }

    public Aluno findByEmail(String email){
        return repository.findByEmail(email);
    }

    @Transactional
    public Aluno getById(Integer id) throws ObjectNotFoundException {
        Optional<Aluno> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(1,
                "Aluno não encontrado.\n Id: " + id));
    }

    @Transactional
    public ResponseEntity<?> insert(AlunoRequestDTO objDTO){
        Aluno aluno = repository.findByEmail(objDTO.getEmail());
        if(aluno != null) return ResponseEntity.badRequest().body(new EmailJaCadastradoException("Email já cadastrado", objDTO.getEmail()));
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

    @Transactional
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
