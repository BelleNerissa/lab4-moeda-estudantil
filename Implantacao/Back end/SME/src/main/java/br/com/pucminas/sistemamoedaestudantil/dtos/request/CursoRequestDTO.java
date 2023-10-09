package br.com.pucminas.sistemamoedaestudantil.dtos.request;

import br.com.pucminas.sistemamoedaestudantil.entities.Curso;
import br.com.pucminas.sistemamoedaestudantil.entities.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoRequestDTO {
    private String nome;
    private Integer id;


    public CursoRequestDTO(Curso curso){

        this.nome = curso.getNome();
        this.id = curso.getId();
    }

    public Curso build(){
        Curso curso = new Curso();
        curso.setNome(this.nome);
        return curso;
    }
}
