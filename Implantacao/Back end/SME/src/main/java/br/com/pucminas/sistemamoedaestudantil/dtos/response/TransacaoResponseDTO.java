package br.com.pucminas.sistemamoedaestudantil.dtos.response;

import br.com.pucminas.sistemamoedaestudantil.entities.Transacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoResponseDTO {
    private Integer id;
    private Integer idAluno;
    private String nomeAluno;
    private Integer idProfessor;
    private String nomeProfessor;
    private double valor;
    private String de;
    private String para;

    private String descricao;

//    private double saldoAtual;

    public TransacaoResponseDTO(Transacao transacao){
        this.id = transacao.getId();
        this.idAluno = transacao.getAluno().getId();
        this.idProfessor = transacao.getProfessor().getId();
        this.nomeAluno = transacao.getAluno().getNome();
        this.nomeProfessor = transacao.getProfessor().getNome();
        this.valor = transacao.getValor();
        this.de = transacao.getDe();
        this.para = transacao.getPara();
        this.descricao = transacao.getDescricao();
    }
}
