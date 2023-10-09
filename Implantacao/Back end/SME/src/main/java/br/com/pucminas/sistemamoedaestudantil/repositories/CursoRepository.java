package br.com.pucminas.sistemamoedaestudantil.repositories;

import br.com.pucminas.sistemamoedaestudantil.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
