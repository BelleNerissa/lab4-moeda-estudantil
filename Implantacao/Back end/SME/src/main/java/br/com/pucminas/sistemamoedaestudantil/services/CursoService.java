package br.com.pucminas.sistemamoedaestudantil.services;

import br.com.pucminas.sistemamoedaestudantil.dtos.request.CursoRequestDTO;

import br.com.pucminas.sistemamoedaestudantil.dtos.response.CursoResponseDTO;

import br.com.pucminas.sistemamoedaestudantil.entities.Curso;

import br.com.pucminas.sistemamoedaestudantil.repositories.CursoRepository;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoService {
    private CursoRepository repository;

    @Transactional
    public List<CursoResponseDTO> findAll(){
        List<Curso> listResponse = repository.findAll();
        return listResponse.stream().map(obj -> new CursoResponseDTO(obj)).collect(Collectors.toList());
    }

    @Transactional
    public Curso getById(Integer id) throws Exception {
        try{
            Optional<Curso> obj = repository.findById(id);
            return obj.orElseThrow(()-> new ObjectNotFoundException(1,
                    "Curso não encontrado.\n Id: " + id));
        }catch(Exception e){
            throw new Exception("Curso não encontrado");
        }
    }

    @Transactional
    public ResponseEntity<CursoRequestDTO> insert(CursoRequestDTO objDTO){
        Curso obj = repository.save(objDTO.build());
        return ResponseEntity.ok().body(new CursoRequestDTO(obj));
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

    public Curso update(Integer id, Curso obj) throws Exception {
        Curso newCurso = getById(id);
        newCurso.setNome(obj.getNome());
        return repository.save(newCurso);
    }
}
