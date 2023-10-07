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
@AllArgsConstructor
@NoArgsConstructor
public class Empresa implements Serializable {
    private static final long SerialVersionUID = 3298741341L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String cnpj;
    Double saldo;
}
