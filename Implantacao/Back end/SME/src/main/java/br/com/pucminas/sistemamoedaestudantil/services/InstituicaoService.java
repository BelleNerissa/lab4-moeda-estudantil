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

    @Transactional
    public List<InstituicaoResponseDTO> findAll(){
        List<Instituicao> listResponse = repository.findAll();
        return listResponse.stream().map(obj -> new InstituicaoResponseDTO(obj)).collect(Collectors.toList());
    }

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

    @Transactional
    public ResponseEntity<InstituicaoRequestDTO> insert(InstituicaoRequestDTO objDTO){
        Instituicao obj = repository.save(objDTO.build());
        return ResponseEntity.ok().body(new InstituicaoRequestDTO(obj));
    }

    @Transactional
    public void deleteById(Integer id) throws Exception {
        getById(id);
        try{
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Não foi possivel excluir esta instituição.");
        }
    }

    public Instituicao update(Integer id, Instituicao obj) throws Exception {
        Instituicao newInstituicao = getById(id);
        newInstituicao.setNome(obj.getNome());
        return repository.save(newInstituicao);
    }
}
