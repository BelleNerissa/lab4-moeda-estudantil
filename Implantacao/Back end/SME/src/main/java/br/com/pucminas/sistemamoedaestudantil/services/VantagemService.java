package br.com.pucminas.sistemamoedaestudantil.services;

import br.com.pucminas.sistemamoedaestudantil.dtos.request.EmpresaRequestDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.request.VantagemRequestDTO;
import br.com.pucminas.sistemamoedaestudantil.entities.Empresa;
import br.com.pucminas.sistemamoedaestudantil.entities.Vantagem;
import br.com.pucminas.sistemamoedaestudantil.repositories.TransacaoRepository;
import br.com.pucminas.sistemamoedaestudantil.repositories.VantagemRepository;
import org.aspectj.apache.bcel.classfile.LocalVariableTable;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.InvalidTransactionException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VantagemService {

    @Autowired
    private VantagemRepository repository;

    @Autowired
    private EmpresaService empresaService;


    @Transactional
    public Vantagem getById(Integer id) throws ObjectNotFoundException {
        try{
        Optional<Vantagem> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(1,
                "Vantagem não encontrada.\n Id: " + id));

        }catch(ObjectNotFoundException e){
            throw new ObjectNotFoundException(404, "Vantagem não encontrada");
        }

    }

    @Transactional
    public Vantagem addVantagem(VantagemRequestDTO objDTO) {
            Empresa emp = empresaService.getById(objDTO.getEmpresaId());
            Vantagem vantagem = objDTO.build();
            vantagem.setEmpresa(emp);
        return repository.save(vantagem);
    }

    @Transactional
    public List<Vantagem> listarVantagem() {
        List<Vantagem>  listaVantagem = repository.findAll();

        return listaVantagem;

    }

    @Transactional
    public List<Vantagem> listarVantagemByEmpresaId(Integer id) throws ObjectNotFoundException{
        List<Vantagem> vantagens = repository.findAllByEmpresaId(id);

        if(vantagens.size() > 0)
            return vantagens;

         throw new ObjectNotFoundException(1, "Vantagem não encontrada.\n Id: " + id);
    }

    @Transactional
    public void deleteById(Integer id) throws Exception {
        getById(id);
        try{
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Não foi possivel essa vantagem");
        }
    }
}
