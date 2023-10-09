package br.com.pucminas.sistemamoedaestudantil.Exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailJaCadastradoException extends Exception{

    String message;
    String email;
}
