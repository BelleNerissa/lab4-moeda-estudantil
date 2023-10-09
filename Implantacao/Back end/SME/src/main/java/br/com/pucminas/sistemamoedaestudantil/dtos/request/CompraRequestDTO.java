package br.com.pucminas.sistemamoedaestudantil.dtos.request;

import br.com.pucminas.sistemamoedaestudantil.entities.Compra;
import br.com.pucminas.sistemamoedaestudantil.entities.Vantagem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraRequestDTO {

    private Integer id;

    private Integer alunoId;

    private List<Integer> vantagensIds;

    private Double valor;

    public CompraRequestDTO(Compra obj){
        vantagensIds = new ArrayList<>();
        this.id = obj.getId();
        this.alunoId = obj.getAluno().getId();
        this.vantagensIds = obj.getVantagens().stream().map(Vantagem::getId).collect(Collectors.toList());
        this.valor = obj.getValor();
    }
}
