package br.com.pucminas.sistemamoedaestudantil.dtos.response;

import br.com.pucminas.sistemamoedaestudantil.entities.Aluno;
import br.com.pucminas.sistemamoedaestudantil.entities.Compra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private Integer roleID;
    private String cpf;

    private List<CompraResponseDTO> compras;

    public AlunoResponseDTO(Aluno aluno){
        this.compras = new ArrayList<>();
        this.id = aluno.getId();
        this.email = aluno.getEmail();
        this.endereco = aluno.getEndereco();
        this.nome = aluno.getNome();
        this.rg = aluno.getRg();
        this.saldo = aluno.getSaldo();
        this.cpf = aluno.getCpf();
        this.roleID = 1;
        this.compras = aluno.getCompras().stream().map(CompraResponseDTO::new).collect(Collectors.toList());
    }
}
