package br.com.pucminas.sistemamoedaestudantil.dtos.response;

import br.com.pucminas.sistemamoedaestudantil.entities.Compra;
import br.com.pucminas.sistemamoedaestudantil.entities.Vantagem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraResponseDTO {

    private Integer id;
    private Integer alunoId;
    private List<VantagemResponseDTO> vantagens;
    private String alunoEmail;
    private Double valor;

    public CompraResponseDTO(Compra obj){
        vantagens = new ArrayList<>();
        this.id = obj.getId();
        this.alunoId = obj.getAluno().getId();
        this.valor = obj.getValor();
        this.vantagens= obj.getVantagens().stream().map(VantagemResponseDTO::new).collect(Collectors.toList());
        this.alunoEmail = obj.getAluno().getEmail();
    }
}
