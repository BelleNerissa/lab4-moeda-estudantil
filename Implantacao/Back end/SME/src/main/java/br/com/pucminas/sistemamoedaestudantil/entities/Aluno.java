package br.com.pucminas.sistemamoedaestudantil.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno extends Usuario implements Serializable {
    private static final long SerialVersionUID = 329877213L;

    private String rg;
    private String cpf;
    private String endereco;
    private Double saldo;
    private final Integer roleID = 1;

    @OneToMany
    @JoinColumn(name = "aluno_id")
    @JsonIgnore
    private Set<Transacao> transacoes;

    @OneToMany
    @JoinColumn(name="aluno_id")
    @JsonManagedReference
    private List<Compra> compras;


}
