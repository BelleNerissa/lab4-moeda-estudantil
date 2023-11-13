package br.com.pucminas.sistemamoedaestudantil.services;

import br.com.pucminas.sistemamoedaestudantil.dtos.request.CompraRequestDTO;
import br.com.pucminas.sistemamoedaestudantil.entities.Aluno;
import br.com.pucminas.sistemamoedaestudantil.entities.Compra;
import br.com.pucminas.sistemamoedaestudantil.entities.Vantagem;
import br.com.pucminas.sistemamoedaestudantil.repositories.AlunoRepository;
import br.com.pucminas.sistemamoedaestudantil.repositories.CompraRepository;
import br.com.pucminas.sistemamoedaestudantil.repositories.VantagemRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompraService {

    @Autowired
    private CompraRepository repository;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private VantagemService vantagemService;

    /**
     * Método que busca uma compra pelo ID.
     * @param id id da compra.
     * @return compra feita.
     * */
    public Compra getById(Integer id) throws ObjectNotFoundException {
        Optional<Compra> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(1,
                "Transacao não encontrada.\n Id: " + id));
    }

    /**
     * Método busca todas as compras associadas a um aluno identificado pelo ID.
     * @param id id do aluno.
     * @return retorna a lista de compras.
     * */
    public List<Compra> getByAlunoId(Integer id) throws ObjectNotFoundException{
        Aluno aluno;
        try{
            aluno = alunoService.getById(id);
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException(404,"Aluno não encontrado");
        }
        List<Compra> list = aluno.getCompras();
        if(list.size() > 0)
            return list;
        throw new ObjectNotFoundException(404,"O aluno não possui compras");
    }

    /**
     * Método busca todas as compras associadas a uma vantagem identificada pelo ID.
     * @param id id da vantagem.
     * @return retorna a lista de compras de acordo com a vantagem.
     * */
    public List<Compra> getByVantagemId(Integer id) throws ObjectNotFoundException{
        Vantagem vantagem;
        try{
            vantagem = vantagemService.getById(id);
        }catch (ObjectNotFoundException e){
            throw new ObjectNotFoundException(404,"Vantagem não encontrada");
        }
        List<Compra> list = vantagem.getCompras();
        if(list.size() > 0)
            return list;
        throw new ObjectNotFoundException(404,"A vantagem não possui compras");
    }

    /**
     * Método insere uma nova compra no repositório.
     * @param objDTO objeto dto da compra.
     * @return o resultado.
     * */
    public Compra insert(CompraRequestDTO objDTO) throws Exception, ObjectNotFoundException{

        try {
            Compra obj = fromDTO(objDTO);
            calcularValor(obj);
            alunoService.subtrairMoedas(obj.getValor(), obj.getAluno().getId());
            return repository.save(obj);
        }catch(ObjectNotFoundException e) {
            throw new ObjectNotFoundException(404, "Aluno não encontrado");
        }catch(Exception e){
            throw new Exception("Produto já comprado");
        }
    }

    /**
     * Método calcula o valor total de uma compra somando os valores de todas as vantagens associadas à compra.
     * @param obj objeto compra.
     * */
    public void calcularValor(Compra obj){
        obj.setValor(obj.getVantagens().stream().map(Vantagem::getValor).reduce(Double::sum).get());
    }

    /**
     * Método converte um CompraRequestDTO para um objeto Compra
     * @param objDTO objeto dto.
     * @return o objeto convertido em compra.
     * */
    private Compra fromDTO(CompraRequestDTO objDTO){
        Compra obj = new Compra();
        obj.setAluno(alunoService.getById(objDTO.getAlunoId()));
        obj.setValor(objDTO.getValor());
        obj.setVantagens(objDTO.getVantagensIds().stream().map(id -> vantagemService.getById(id)).collect(Collectors.toList()));
        obj.setId(objDTO.getId());
        return obj;
    }
}
