package br.com.pucminas.sistemamoedaestudantil.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transacao implements Serializable {
    private static final long SerialVersionUID = 3799421812L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    private Professor professor;

    @ManyToOne
    private Aluno aluno;

    private double valor;

    private String de;

    private String para;

    private double saldoAtual;

    private String descricao;
}
