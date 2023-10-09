package br.com.pucminas.sistemamoedaestudantil.dtos.request;

import br.com.pucminas.sistemamoedaestudantil.entities.Empresa;
import br.com.pucminas.sistemamoedaestudantil.entities.Vantagem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaRequestDTO {

    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private String cnpj;
    private double saldo;
    private List<Vantagem> vantagem;

    public EmpresaRequestDTO(Empresa obj){
        this.nome = obj.getNome();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.cnpj = obj.getCnpj();
        this.saldo = obj.getSaldo();
        this.id = obj.getId();
        this.vantagem = obj.getVantagems();
    }

    public Empresa build(){
        Empresa obj = new Empresa();
        obj.setNome(this.nome);
        obj.setEmail(this.email);
        obj.setCnpj(this.cnpj);
        obj.setSaldo(this.saldo);
        obj.setSenha(this.senha);
        obj.setVantagems(this.vantagem);
        return obj;
    }
}
