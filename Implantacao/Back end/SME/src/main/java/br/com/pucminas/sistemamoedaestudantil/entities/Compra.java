package br.com.pucminas.sistemamoedaestudantil.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Compra implements Serializable {
    private static final long SerialVersionUID = 48732L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonBackReference
    private Aluno aluno;

    @ManyToOne
    @JsonBackReference
    private Empresa empresa;

    @OneToMany
    private List<Vantagem> vantagens = new ArrayList<>();

    private Double valor;

}
