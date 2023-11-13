package br.com.pucminas.sistemamoedaestudantil.services;

import br.com.pucminas.sistemamoedaestudantil.Exception.EmailJaCadastradoException;
import br.com.pucminas.sistemamoedaestudantil.dtos.request.EmpresaRequestDTO;
import br.com.pucminas.sistemamoedaestudantil.dtos.response.EmpresaResponseDTO;
import br.com.pucminas.sistemamoedaestudantil.entities.Aluno;
import br.com.pucminas.sistemamoedaestudantil.entities.Empresa;
import br.com.pucminas.sistemamoedaestudantil.entities.Vantagem;
import br.com.pucminas.sistemamoedaestudantil.repositories.EmpresaRepository;
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
public class EmpresaService {

    @Autowired
    private EmpresaRepository repository;

    /**
     * Busca todas as empresas disponíveis no repositório e as converte para {@link EmpresaResponseDTO}.
     *
     * @return Uma lista de {@link EmpresaResponseDTO} representando todas as empresas encontradas.
     */
    @Transactional
    public List<EmpresaResponseDTO> findAll(){
        List<Empresa> listResponse = repository.findAll();
        return listResponse.stream().map(EmpresaResponseDTO::new).collect(Collectors.toList());
    }

    /**
     * Busca uma empresa específica pelo ID.
     *
     * @param id O ID da empresa a ser buscada.
     * @return A empresa encontrada.
     * @throws ObjectNotFoundException Se a empresa não for encontrada.
     */
    @Transactional
    public Empresa getById(Integer id){
        Optional<Empresa> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(1,
                "Empresa não encontrada.\n Id: " + id));

    }

    /**
     * Insere uma nova empresa no repositório.
     *
     * @param objDTO O DTO da empresa a ser inserida.
     * @return Uma {@link ResponseEntity} com o DTO da empresa inserida ou uma mensagem de erro se o email já estiver cadastrado.
     */
    @Transactional
    public ResponseEntity<?> insert(EmpresaRequestDTO objDTO){
        Empresa empresa = repository.findByEmail(objDTO.getEmail());
        if(empresa != null) return ResponseEntity.badRequest().body(new EmailJaCadastradoException("Email já cadastrado", objDTO.getEmail()));
        Empresa obj = repository.save(objDTO.build());
        return ResponseEntity.ok().body(new EmpresaRequestDTO(obj));
    }

    /**
     * Busca uma empresa pelo email.
     *
     * @param email O email da empresa a ser buscada.
     * @return A empresa encontrada.
     */
    public Empresa findByEmail(String email){
        return repository.findByEmail(email);
    }

    /**
     * Exclui uma empresa pelo ID.
     *
     * @param id O ID da empresa a ser excluída.
     * @throws DataIntegrityViolationException Se a exclusão violar a integridade dos dados.
     */
    @Transactional
    public void deleteById(Integer id){
        getById(id);
        try{
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Não foi possivel excluir este aluno.");
        }
    }

    /**
     * Atualiza as informações de uma empresa.
     *
     * @param id O ID da empresa a ser atualizada.
     * @param obj O objeto {@link Empresa} contendo as novas informações.
     * @return A empresa atualizada.
     */
    @Transactional
    public Empresa update(Integer id, Empresa obj) {
        Empresa newEmpresa = getById(id);
        newEmpresa.setCnpj(obj.getCnpj());
        newEmpresa.setSaldo(obj.getSaldo());
        newEmpresa.setNome(obj.getNome());
        newEmpresa.setEmail(obj.getEmail());
        newEmpresa.setSenha(obj.getSenha());
        return repository.save(newEmpresa);
    }
    
}
