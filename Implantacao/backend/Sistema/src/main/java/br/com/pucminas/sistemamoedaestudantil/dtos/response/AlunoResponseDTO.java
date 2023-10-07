package br.com.pucminas.sistemamoedaestudantil.dtos.response;

import br.com.pucminas.sistemamoedaestudantil.entities.Aluno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoResponseDTO {

    private Integer id;
    private String nome;
    private String email;
    private String rg;
    private String endereco;
    private Double saldo;

    private String cpf;

    public AlunoResponseDTO(Aluno aluno){
        this.id = aluno.getId();
        this.email = aluno.getEmail();
        this.endereco = aluno.getEndereco();
        this.nome = aluno.getNome();
        this.rg = aluno.getRg();
        this.saldo = aluno.getSaldo();
        this.cpf = aluno.getCpf();
    }
}
