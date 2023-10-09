package br.com.pucminas.sistemamoedaestudantil.repositories;


import br.com.pucminas.sistemamoedaestudantil.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    Professor findByEmail(String email);

}
