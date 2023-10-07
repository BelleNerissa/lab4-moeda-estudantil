package br.com.pucminas.sistemamoedaestudantil.dtos.request;

import br.com.pucminas.sistemamoedaestudantil.entities.Aluno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoRequestDTO {

    String nome;
    String email;
    String senha;
    String rg;

    String cpf;
    String endereco;
    Double saldo;

    public AlunoRequestDTO(Aluno aluno){
        this.email = aluno.getEmail();
        this.endereco = aluno.getEndereco();
        this.nome = aluno.getNome();
        this.rg = aluno.getRg();
        this.senha = aluno.getSenha();
        this.saldo = aluno.getSaldo();
        this.cpf = aluno.getCpf();
    }

    public Aluno build(){
        Aluno aluno = new Aluno();
        aluno.setNome(this.nome);
        aluno.setEmail(this.email);
        aluno.setSenha(this.senha);
        aluno.setRg(this.rg);
        aluno.setEndereco(this.endereco);
        aluno.setSaldo(this.saldo);
        aluno.setCpf(this.cpf);
        return aluno;
    }
}
