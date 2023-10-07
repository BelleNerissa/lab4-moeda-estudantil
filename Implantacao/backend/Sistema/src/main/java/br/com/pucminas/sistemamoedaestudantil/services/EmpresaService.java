package br.com.pucminas.sistemamoedaestudantil.services;

import br.com.pucminas.sistemamoedaestudantil.entities.Empresa;
import br.com.pucminas.sistemamoedaestudantil.repositories.EmpresaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository repository;

    @Transactional
    public List<Empresa> findAll(){
        List<Empresa> listResponse = repository.findAll();
        return listResponse;
    }

    @Transactional
    public Empresa getById(Integer id){
        Optional<Empresa> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(1,
                "Aluno não encontrado.\n Id: " + id));
    }

    @Transactional
    public ResponseEntity<Empresa> insert(Empresa obj){
        Empresa resp = repository.save(obj);
        return ResponseEntity.ok().body(resp);
    }

    @Transactional
    public void deleteById(Integer id){
        getById(id);
        try{
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Não foi possivel excluir este aluno.");
        }
    }

    public Empresa update(Integer id, Empresa obj) {
        Empresa newEmpresa = getById(id);
        newEmpresa.setCnpj(obj.getCnpj());
        newEmpresa.setSaldo(obj.getSaldo());
        return repository.save(newEmpresa);
    }
    
}
