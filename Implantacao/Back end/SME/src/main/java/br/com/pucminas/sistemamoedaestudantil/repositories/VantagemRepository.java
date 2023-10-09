package br.com.pucminas.sistemamoedaestudantil.repositories;

import br.com.pucminas.sistemamoedaestudantil.entities.Vantagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VantagemRepository extends JpaRepository<Vantagem, Integer> {

    List<Vantagem> findAllByEmpresaId(Integer id);

}
