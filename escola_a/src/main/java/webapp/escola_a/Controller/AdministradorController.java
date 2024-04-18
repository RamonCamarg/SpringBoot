package webapp.escola_a.Controller;

import org.aspectj.weaver.bcel.ExceptionRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import webapp.escola_a.Model.Administrador;
import webapp.escola_a.Repository.AdministradorRepository;
import webapp.escola_a.Repository.PreCadAdmRepository;

@Controller
public class AdministradorController {
    @Autowired
    private AdministradorRepository admr;

    @Autowired
    private PreCadAdmRepository pcar;

    boolean acessoAdm = false;
    
    @PostMapping("cadastro-adm")
    public String cadastrarAdmBD(Administrador adm) {
        boolean verificaCpf = pcar.existsById(adm.getCpf());
        if (verificaCpf) {
            admr.save(adm);
            System.out.println("Cadastro Realizado com Sucesso");
        } else {
            System.out.println("Falha ao Cadastrar");
        }
        return "/login/login-adm";
    }

    @PostMapping("login-adm")
    public String loginADM(Administrador adm) {
        boolean verificaCpf = pcar.existsById(adm.getCpf());
        if (verificaCpf) {
            admr.save(adm);
            System.out.println("Login Realizado com sucesso");
            return "/interna/interna-adm";
        } else{
            System.out.println("Falha ao tentar Fazer Login");
            return "/login/login-adm";
        }
    
    }
    
    
    @GetMapping("/interna-adm")
    public String acessoPageInternaAdm() {
        String vaiPara = "";
        if (acessoAdm) {
            vaiPara = "interna/interna-adm";
        } else {
            vaiPara = "login/login-adm";
        }
        return vaiPara;
    }

        @PostMapping("acesso-adm")
    public ModelAndView acessoAdm(@RequestParam String cpf,
            @RequestParam String senha) {
        ModelAndView mv = new ModelAndView();
        try {
            boolean verificaCpf = admr.existsById(cpf);
            boolean verificaSenha = admr.findByCpf(cpf).getSenha().equals(senha);
            String url = "";
            if (verificaCpf && verificaSenha) {
                acessoAdm = true;
                url = "redirect:/interna-adm";
                mv.addObject("msg", "Logado com sucesso!");
                mv.setViewName(url);
            } else {
                url = "redirect:/login-adm";
                mv.addObject("msg", "Erro! Acesso negado ou senha incorreta.");
                mv.setViewName(url);
            }
            return mv;
        } catch (Exception e) {
            mv.addObject("msg", "Erro no login.");
            mv.setViewName("redirect:/login-adm");
            return mv;
        }

    }

}
