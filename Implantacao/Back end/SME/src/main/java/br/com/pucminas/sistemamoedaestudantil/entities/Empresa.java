package br.com.pucminas.sistemamoedaestudantil.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empresa extends Usuario implements Serializable {
    private static final long SerialVersionUID = 3298741341L;

    String cnpj;
    Double saldo;
    @OneToMany(mappedBy = "empresa")
    @JsonIgnoreProperties("empresa")
    private List<Vantagem> vantagems = new ArrayList<>();
    private final Integer roleID = 3;

    /**
     * Método que adiciona vantagem a empresa.
     * @param obj recebe o objeto vantagem.
     * */
    public void addVantagem(Vantagem obj)
    {
        vantagems.add(obj);
    }
}
