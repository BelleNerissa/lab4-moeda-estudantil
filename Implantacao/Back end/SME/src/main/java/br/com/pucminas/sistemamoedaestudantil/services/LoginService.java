package br.com.pucminas.sistemamoedaestudantil.services;

import br.com.pucminas.sistemamoedaestudantil.entities.Aluno;
import br.com.pucminas.sistemamoedaestudantil.entities.Empresa;
import br.com.pucminas.sistemamoedaestudantil.entities.Professor;

import br.com.pucminas.sistemamoedaestudantil.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private EmpresaService empresaService;

    /**
     * Valida o login de um usuário com base no email e senha fornecidos.]
     *
     * @param email O email do usuário a ser validado.
     * @param senha A senha do usuário a ser validada.
     * @return O {@link Usuario} correspondente se as credenciais forem válidas.
     * @throws IllegalArgumentException Se as credenciais não corresponderem a nenhum usuário ou se a senha estiver incorreta.
     */
    public Usuario validarLogin(String email, String senha) throws IllegalArgumentException{
        Usuario user;
        user = alunoService.findByEmail(email);
        if(user != null){
            if(user.getSenha().equals(senha))
                return user;
        }
        user = professorService.findByEmail(email);
        if(user != null){
            if(user.getSenha().equals(senha))
                return user;
        }
        user = empresaService.findByEmail(email);
        if(user != null){
            if(user.getSenha().equals(senha))
                return user;
        }
        throw new IllegalArgumentException("Dados não conferem");
    }

//    public Aluno validateAlunoLogin(String email, String senha) throws IllegalArgumentException {
//        Aluno obj = alunoService.findByEmail(email);
//        if(obj == null) throw new IllegalArgumentException("Email não cadastrado!");
//        else if(!obj.getSenha().equals(senha)) throw new IllegalArgumentException("Senha incorreta");
//        return obj;
//    }
//
//    public Professor validateProfessorLogin(String email, String senha) throws IllegalArgumentException {
//        Professor obj = professorService.findByEmail(email);
//        if(obj == null) throw new IllegalArgumentException("Email não cadastrado!");
//        else if(!obj.getSenha().equals(senha)) throw new IllegalArgumentException("Senha incorreta");
//        return obj;
//    }
//    public Empresa validateEmpresaLogin(String email, String senha) throws IllegalArgumentException {
//        Empresa obj = empresaService.findByEmail(email);
//        if(obj == null) throw new IllegalArgumentException("Email não cadastrado!");
//        else if(!obj.getSenha().equals(senha)) throw new IllegalArgumentException("Senha incorreta");
//        return obj;
//    }

    /**
     * Verifica se um email já está cadastrado em algum dos serviços de usuário (aluno, professor, empresa).
     *
     * @param email O email a ser verificado.
     * @return {@code true} se o email já estiver cadastrado em algum serviço; {@code false} caso contrário.
     */
    public boolean emailJaCadastrado(String email){
        Usuario user;
        user = alunoService.findByEmail(email);
        if(user != null) return true;
        user = professorService.findByEmail(email);
        if(user != null) return true;
        user = empresaService.findByEmail(email);
        if(user != null) return true;
        return false;
    }
}
