package br.com.pucminas.sistemamoedaestudantil.services;

import br.com.pucminas.sistemamoedaestudantil.dtos.request.CursoRequestDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.request.InstituicaoRequestDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.response.CursoResponseDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.response.InstituicaoResponseDTO;
import br.com.pucminas.sistemamoedaestudantil.entities.Curso;
import br.com.pucminas.sistemamoedaestudantil.entities.Instituicao;
import br.com.pucminas.sistemamoedaestudantil.repositories.CursoRepository;
import br.com.pucminas.sistemamoedaestudantil.repositories.InstituicaoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstituicaoService {
    private InstituicaoRepository repository;

    /**
     * Busca todas as instituições disponíveis no repositório e as converte para {@link InstituicaoResponseDTO}.
     *
     * @return Uma lista de {@link InstituicaoResponseDTO} representando todas as instituições encontradas.
     */
    @Transactional
    public List<InstituicaoResponseDTO> findAll(){
        List<Instituicao> listResponse = repository.findAll();
        return listResponse.stream().map(obj -> new InstituicaoResponseDTO(obj)).collect(Collectors.toList());
    }

    /**
     * Busca uma instituição específica pelo ID.
     *
     * @param id O ID da instituição a ser buscada.
     * @return A instituição encontrada.
     * @throws Exception Se a instituição não for encontrada.
     */
    @Transactional
    public Instituicao getById(Integer id) throws Exception {
        try{
            Optional<Instituicao> obj = repository.findById(id);
            return obj.orElseThrow(()-> new ObjectNotFoundException(1,
                    "Instituicao não encontrada.\n Id: " + id));
        }catch(Exception e){
            throw new Exception("Instituição não encontrada");
        }
    }

    /**
     * Insere uma nova instituição no repositório.
     *
     * @param objDTO O DTO da instituição a ser inserida.
     * @return Uma {@link ResponseEntity} com o DTO da instituição inserida.
     */
    @Transactional
    public ResponseEntity<InstituicaoRequestDTO> insert(InstituicaoRequestDTO objDTO){
        Instituicao obj = repository.save(objDTO.build());
        return ResponseEntity.ok().body(new InstituicaoRequestDTO(obj));
    }

    /**
     * Exclui uma instituição pelo ID.
     *
     * @param id O ID da instituição a ser excluída.
     * @throws Exception Se a instituição não for encontrada ou se a exclusão violar a integridade dos dados.
     */
    @Transactional
    public void deleteById(Integer id) throws Exception {
        getById(id);
        try{
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Não foi possivel excluir esta instituição.");
        }
    }

    /**
     * Atualiza as informações de uma instituição.
     *
     * @param id O ID da instituição a ser atualizada.
     * @param obj O objeto {@link Instituicao} contendo as novas informações.
     * @return A instituição atualizada.
     * @throws Exception Se a instituição não for encontrada.
     */
    public Instituicao update(Integer id, Instituicao obj) throws Exception {
        Instituicao newInstituicao = getById(id);
        newInstituicao.setNome(obj.getNome());
        return repository.save(newInstituicao);
    }
}
