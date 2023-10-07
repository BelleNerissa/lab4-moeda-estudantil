package br.com.pucminas.sistemamoedaestudantil.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno extends Usuario implements Serializable {
    private static final long SerialVersionUID = 329877213L;

    String rg;
    String cpf;
    String endereco;
    Double saldo;
}
