package webapp.escola_completo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import webapp.escola_completo.Model.Administrador;
import webapp.escola_completo.Repository.AdministradorRepository;
import webapp.escola_completo.Repository.PreCadAdmRepository;


@Controller
public class AdministradorController {

    @Autowired
    private AdministradorRepository ar;

    @Autowired
    private PreCadAdmRepository pcar;

    @PostMapping("cadastro-adm")
    public String postCadastroAdm(Administrador adm) {

        String cpfVerificacao = pcar.findByCpf(adm.getCpf()).getCpf();

        if(cpfVerificacao.equals(adm.getCpf())){
        ar.save(adm);
        //enviar uma mensagem de cadastro realizada com sucesso
        System.out.println("Cadastro realizado com sucesso");   
        }else{
            System.out.println("Cadastro não Realizado");
        }
        return "login/login-adm";

    }
    
    
}
