package br.com.pucminas.sistemamoedaestudantil.dtos.request;

import br.com.pucminas.sistemamoedaestudantil.entities.Aluno;
import br.com.pucminas.sistemamoedaestudantil.entities.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProfessorRequestDTO {
    private String nome;

    private Integer id;
    private String email;
    private String senha;
    private String cpf;
    private Double moedas;

    public ProfessorRequestDTO(Professor professor){
        this.email = professor.getEmail();
        this.nome = professor.getNome();
        this.senha = professor.getSenha();
        this.moedas = professor.getMoedas();
        this.cpf = professor.getCpf();
        this.id = professor.getId();
    }

    public Professor build(){
        Professor professor = new Professor();
        professor.setNome(this.nome);
        professor.setEmail(this.email);
        professor.setSenha(this.senha);
        professor.setMoedas(this.moedas);
        professor.setCpf(this.cpf);
        return professor;
    }
}
