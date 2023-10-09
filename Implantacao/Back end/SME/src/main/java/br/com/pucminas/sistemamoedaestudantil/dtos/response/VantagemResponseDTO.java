package br.com.pucminas.sistemamoedaestudantil.dtos.response;

import br.com.pucminas.sistemamoedaestudantil.entities.Vantagem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VantagemResponseDTO {
    private Integer id;
    private Integer empresaId;
    private double valor;
    private String descricao;
    private String imagem;

    public VantagemResponseDTO(Vantagem vantagem){
        this.id = vantagem.getId();
        this.empresaId = vantagem.getEmpresa().getId();
        this.valor = vantagem.getValor();
        this.descricao = vantagem.getDescricao();
        this.imagem = vantagem.getImagem();
    }
}
