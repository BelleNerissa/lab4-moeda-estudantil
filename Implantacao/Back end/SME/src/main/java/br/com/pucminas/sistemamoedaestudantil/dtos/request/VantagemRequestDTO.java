package br.com.pucminas.sistemamoedaestudantil.dtos.request;

import br.com.pucminas.sistemamoedaestudantil.entities.Vantagem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VantagemRequestDTO {

    private Integer id;
    private Integer empresaId;
    private double valor;
    private String descricao;
    private String imagem;


    public VantagemRequestDTO(Vantagem obj){
        this.id = obj.getId();
        this.empresaId = obj.getEmpresa().getId();
        this.valor = obj.getValor();
        this.descricao = obj.getDescricao();
        this.imagem = obj.getImagem();
    }

    public Vantagem build(){
      Vantagem vantagem = new Vantagem();
      vantagem.setId(this.id);
      vantagem.setValor(this.valor);
      vantagem.setDescricao(this.descricao);
      vantagem.setImagem(this.imagem);
      return vantagem;
    }
}
