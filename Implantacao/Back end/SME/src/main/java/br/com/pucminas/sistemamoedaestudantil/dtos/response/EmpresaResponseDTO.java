package br.com.pucminas.sistemamoedaestudantil.dtos.response;

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
public class EmpresaResponseDTO {
    private String nome;

    private Integer id;
    private String email;
    private String senha;
    private String cnpj;
    private double saldo;
    private List<Vantagem> vantagens;
    private Integer roleID;


    public EmpresaResponseDTO(Empresa obj){
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.cnpj = obj.getCnpj();
        this.saldo = obj.getSaldo();
        this.vantagens = obj.getVantagems();
        this.roleID = 3;
    }
}
