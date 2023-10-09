package br.com.pucminas.sistemamoedaestudantil.repositories;

import br.com.pucminas.sistemamoedaestudantil.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

    List<Transacao> findAllByAluno_Id(Integer id);

    List<Transacao> findAllByProfessor_Id(Integer id);

}
