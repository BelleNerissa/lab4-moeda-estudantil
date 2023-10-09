package br.com.pucminas.sistemamoedaestudantil.dtos.response;

import br.com.pucminas.sistemamoedaestudantil.entities.Curso;
import br.com.pucminas.sistemamoedaestudantil.entities.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoResponseDTO {

    private Integer id;
    private String nome;

    public CursoResponseDTO(Curso curso){
        this.nome = curso.getNome();
        this.id = curso.getId();
    }
}
