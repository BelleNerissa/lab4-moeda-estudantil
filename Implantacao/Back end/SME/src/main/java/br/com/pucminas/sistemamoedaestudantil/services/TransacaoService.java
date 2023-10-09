package br.com.pucminas.sistemamoedaestudantil.services;

import br.com.pucminas.sistemamoedaestudantil.dtos.request.ProfessorRequestDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.request.TransacaoRequestDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.response.ProfessorResponseDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.response.TransacaoResponseDTO;
import br.com.pucminas.sistemamoedaestudantil.entities.Professor;
import br.com.pucminas.sistemamoedaestudantil.entities.Transacao;
import br.com.pucminas.sistemamoedaestudantil.repositories.ProfessorRepository;
import br.com.pucminas.sistemamoedaestudantil.repositories.TransacaoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.InvalidTransactionException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository repository;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AlunoService alunoService;

    @Transactional
    public Transacao getById(Integer id) throws ObjectNotFoundException {
        Optional<Transacao> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(1,
                "Transacao não encontrada.\n Id: " + id));
    }

    @Transactional
    public ResponseEntity<?> insertByProfessor(TransacaoRequestDTO objDTO) throws Exception {
        try{
            professorService.subtrairMoedas(objDTO.getValor(), objDTO.getProfessorId());
            alunoService.adicionarMoedas(objDTO.getValor(), objDTO.getAlunoId());
            Transacao obj = fromDTO(objDTO);
            obj.setDe("Professor");
            obj.setPara("Aluno");
            obj = repository.save(obj);
            return ResponseEntity.ok().body(new TransacaoRequestDTO(obj));
        }catch(InvalidTransactionException e){
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<?> insertByAluno(TransacaoRequestDTO objDTO) throws Exception {
        try{
            alunoService.subtrairMoedas(objDTO.getValor(), objDTO.getProfessorId());
            professorService.adicionarMoedas(objDTO.getValor(), objDTO.getAlunoId());
        }catch(InvalidTransactionException e){
            return ResponseEntity.ok().body(e.getMessage());
        }
        Transacao obj = repository.save(fromDTO(objDTO));
        return ResponseEntity.ok().body(new TransacaoRequestDTO(obj));
    }

    public List<TransacaoResponseDTO> findAllTransactionByAlunoId(Integer id){
        return repository.findAllByAluno_Id(id).stream().map(TransacaoResponseDTO::new).collect(Collectors.toList());
    }

    public List<TransacaoResponseDTO> findAllTransactionByProfessorId(Integer id){
        return repository.findAllByProfessor_Id(id).stream().map(TransacaoResponseDTO::new).collect(Collectors.toList());
    }

    private Transacao fromDTO(TransacaoRequestDTO objDTO) throws Exception {
        Transacao obj = new Transacao();
        obj.setAluno(alunoService.getById(objDTO.getAlunoId()));
        obj.setProfessor(professorService.getById(objDTO.getProfessorId()));
        obj.setValor(objDTO.getValor());
        obj.setDescricao(objDTO.getDescricao());
        return obj;
    }

    @Transactional
    public void deleteById(Integer id) throws Exception {
        getById(id);
        try{
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Não foi possivel excluir este professor.");
        }
    }
}
