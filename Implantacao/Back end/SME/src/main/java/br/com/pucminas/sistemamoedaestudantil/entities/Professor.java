package br.com.pucminas.sistemamoedaestudantil.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends Usuario implements Serializable {
    private static final long SerialVersionUID = 37995113L;

    private String cpf;
    private Double moedas;

    private final Integer roleID = 2;

    @OneToMany
    @JoinColumn(name = "professor_id")
    @JsonIgnore
    private Set<Transacao> transacoes;

}
