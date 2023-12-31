package br.com.pucminas.sistemamoedaestudantil.dtos.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class MessageDto {

    @NotBlank
    private String ownerAluno;

    @NotBlank
    private String ownerEmpresa;

    @NotBlank
    @Email
    private String from;

    @NotBlank
    @Email
    private String to;

    @NotBlank
    private String title;

    @NotBlank
    private String text;
}