package br.com.pucminas.sistemamoedaestudantil.dtos.response;

import br.com.pucminas.sistemamoedaestudantil.entities.Instituicao;
import br.com.pucminas.sistemamoedaestudantil.entities.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstituicaoResponseDTO {
    private Integer id;
    private String nome;

    private String cnpj;

    public InstituicaoResponseDTO(Instituicao instituicao){
        this.id = instituicao.getId();
        this.nome = instituicao.getNome();

        this.cnpj = instituicao.getCnpj();
    }
}
