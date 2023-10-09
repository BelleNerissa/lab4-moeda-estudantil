package br.com.pucminas.sistemamoedaestudantil.repositories;

import br.com.pucminas.sistemamoedaestudantil.entities.Curso;
import br.com.pucminas.sistemamoedaestudantil.entities.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Integer> {
}
