package br.com.pucminas.sistemamoedaestudantil.dtos.response;

import br.com.pucminas.sistemamoedaestudantil.entities.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorResponseDTO {
    //sem senha
    private Integer id;
    private String nome;
    private String email;
    private Double moedas;
    private String cpf;
    private Integer roleID;

    public ProfessorResponseDTO(Professor professor){
        this.id = professor.getId();
        this.email = professor.getEmail();
        this.nome = professor.getNome();
        this.moedas = professor.getMoedas();
        this.cpf = professor.getCpf();
        this.roleID = 2;
    }
}
