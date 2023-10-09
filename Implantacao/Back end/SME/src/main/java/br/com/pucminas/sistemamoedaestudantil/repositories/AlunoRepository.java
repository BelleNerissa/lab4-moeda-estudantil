package br.com.pucminas.sistemamoedaestudantil.repositories;

import br.com.pucminas.sistemamoedaestudantil.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    Aluno findByEmail(String email);
}
