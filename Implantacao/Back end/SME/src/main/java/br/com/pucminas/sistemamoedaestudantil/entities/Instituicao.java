package br.com.pucminas.sistemamoedaestudantil.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instituicao implements Serializable {
    private static final long SerialVersionUID = 321279468L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    private String nome;
    private String cnpj;
}
