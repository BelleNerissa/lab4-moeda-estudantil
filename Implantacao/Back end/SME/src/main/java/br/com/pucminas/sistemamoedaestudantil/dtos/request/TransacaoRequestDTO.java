package br.com.pucminas.sistemamoedaestudantil.dtos.request;


import br.com.pucminas.sistemamoedaestudantil.entities.Transacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoRequestDTO {

    private Integer id;
    private Integer alunoId;
    private Integer professorId;
    private double valor;
    private String descricao;

    public TransacaoRequestDTO(Transacao obj){
        this.alunoId = obj.getAluno().getId();
        this.professorId = obj.getProfessor().getId();
        this.valor = obj.getValor();
        this.id = obj.getId();
        this.descricao = obj.getDescricao();
    }
}
