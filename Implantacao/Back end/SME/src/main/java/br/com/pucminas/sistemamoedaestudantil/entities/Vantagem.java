package br.com.pucminas.sistemamoedaestudantil.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vantagem implements Serializable {
    private static final long SerialVersionUID = 3782421882L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Empresa empresa;

    private String descricao;
    private double valor;
    private String imagem;

    @OneToMany
    @JsonIgnore
    private List<Compra> compras;

}
