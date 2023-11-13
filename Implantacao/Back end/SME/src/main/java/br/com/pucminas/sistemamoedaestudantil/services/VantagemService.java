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

    /**
     * Busca uma vantagem específica pelo ID.
     *
     * @param id O ID da vantagem a ser buscada.
     * @return A vantagem encontrada.
     * @throws ObjectNotFoundException Se a vantagem não for encontrada.
     */
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

    /**
     * Adiciona uma nova vantagem ao repositório.
     *
     * @param objDTO O DTO da vantagem a ser adicionada.
     * @return A vantagem adicionada.
     */
    @Transactional
    public Vantagem addVantagem(VantagemRequestDTO objDTO) {
            Empresa emp = empresaService.getById(objDTO.getEmpresaId());
            Vantagem vantagem = objDTO.build();
            vantagem.setEmpresa(emp);
        return repository.save(vantagem);
    }

    /**
     * Lista todas as vantagens disponíveis.
     *
     * @return Uma lista de vantagens.
     */
    @Transactional
    public List<Vantagem> listarVantagem() {
        List<Vantagem>  listaVantagem = repository.findAll();

        return listaVantagem;

    }

    /**
     * Lista todas as vantagens associadas a uma empresa específica.
     *
     * @param id O ID da empresa.
     * @return Uma lista de vantagens.
     * @throws ObjectNotFoundException Se não forem encontradas vantagens para a empresa especificada.
     */
    @Transactional
    public List<Vantagem> listarVantagemByEmpresaId(Integer id) throws ObjectNotFoundException{
        List<Vantagem> vantagens = repository.findAllByEmpresaId(id);

        if(vantagens.size() > 0)
            return vantagens;

         throw new ObjectNotFoundException(1, "Vantagem não encontrada.\n Id: " + id);
    }

    /**
     * Exclui uma vantagem pelo ID.
     *
     * @param id O ID da vantagem a ser excluída.
     * @throws Exception Se a vantagem não for encontrada ou se a exclusão violar a integridade dos dados.
     */
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
