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

    /**
     * Busca uma transação específica pelo ID.
     *
     * @param id O ID da transação a ser buscada.
     * @return A transação encontrada.
     * @throws ObjectNotFoundException Se a transação não for encontrada.
     */
    @Transactional
    public Transacao getById(Integer id) throws ObjectNotFoundException {
        Optional<Transacao> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(1,
                "Transacao não encontrada.\n Id: " + id));
    }

    /**
     * Insere uma nova transação iniciada por um professor.
     *
     * @param objDTO O DTO da transação a ser inserida.
     * @return Uma {@link ResponseEntity} com o DTO da transação inserida.
     * @throws Exception Se ocorrer um erro durante a inserção.
     */
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

    /**
     * Insere uma nova transação iniciada por um aluno.
     *
     * @param objDTO O DTO da transação a ser inserida.
     * @return Uma {@link ResponseEntity} com o DTO da transação inserida.
     * @throws Exception Se ocorrer um erro durante a inserção.
     */
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

    /**
     * Busca todas as transações associadas a um aluno específico.
     *
     * @param id O ID do aluno.
     * @return Uma lista de {@link TransacaoResponseDTO} representando as transações encontradas.
     */
    public List<TransacaoResponseDTO> findAllTransactionByAlunoId(Integer id){
        return repository.findAllByAluno_Id(id).stream().map(TransacaoResponseDTO::new).collect(Collectors.toList());
    }

    /**
     * Busca todas as transações associadas a um professor específico.
     *
     * @param id O ID do professor.
     * @return Uma lista de {@link TransacaoResponseDTO} representando as transações encontradas.
     */
    public List<TransacaoResponseDTO> findAllTransactionByProfessorId(Integer id){
        return repository.findAllByProfessor_Id(id).stream().map(TransacaoResponseDTO::new).collect(Collectors.toList());
    }

    /**
     * Converte um {@link TransacaoRequestDTO} em um objeto {@link Transacao}.
     *
     * @param objDTO O DTO a ser convertido.
     * @return O objeto {@link Transacao} resultante.
     * @throws Exception Se ocorrer um erro durante a conversão.
     */
    private Transacao fromDTO(TransacaoRequestDTO objDTO) throws Exception {
        Transacao obj = new Transacao();
        obj.setAluno(alunoService.getById(objDTO.getAlunoId()));
        obj.setProfessor(professorService.getById(objDTO.getProfessorId()));
        obj.setValor(objDTO.getValor());
        obj.setDescricao(objDTO.getDescricao());
        return obj;
    }

    /**
     * Exclui uma transação pelo ID.
     *
     * @param id O ID da transação a ser excluída.
     * @throws Exception Se a transação não for encontrada ou se a exclusão violar a integridade dos dados.
     */
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
