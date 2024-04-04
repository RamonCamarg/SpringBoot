package webapp.escola_completo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class IndexController {

    @GetMapping("/")
    public String acessoHome() {
        return "index";
    }

    @GetMapping("/home")
    public String acessoHome2() {
        return "index";
    }
    
    @GetMapping("/login-adm")
    public String acessoLoginAdm() {
        return "login/login-adm";
    }

    @GetMapping("/cad-adm")
    public String acessoCadastroAdm() {
        return "cadastro/cad-adm";
    }
    
    
}
