package webapp.escola_a.Controller;

import org.aspectj.weaver.bcel.ExceptionRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import webapp.escola_a.Model.Professor;
import webapp.escola_a.Repository.PreCadProfRepository;
import webapp.escola_a.Repository.ProfRepository;

@Controller
public class ProfController {
    @Autowired
    private ProfRepository prof;

    @Autowired
    private PreCadProfRepository pcpr;

    boolean acessoProf = false;
    
    @PostMapping("cad-prof")
    public String cadastrarProfBD(Professor prof) {
        boolean verificaCpf = pcpr.existsById(prof.getCpf());
        if (verificaCpf) {
            prof.save(prof);
            System.out.println("Cadastro Realizado com Sucesso");
        } else {
            System.out.println("Falha ao Cadastrar");
        }
        return "/login/login-prof";
    }

    @PostMapping("login-prof")
    public String loginProf(Professor prof) {
        boolean verificaCpf = pcpr.existsById(prof.getCpf());
        if (verificaCpf) {
            prof.save(prof);
            System.out.println("Login Realizado com sucesso");
            return "interna/interna-prof";
        } else{
            System.out.println("Falha ao tentar Fazer Login");
            return "/login/login-prof";
        }
    
    }
    
    
    @GetMapping("/interna-prof")
    public String acessoPageInternaProf() {
        String vaiPara = "";
        if (acessoProf) {
            vaiPara = "interna/interna-prof";
        } else {
            vaiPara = "login/login-prof";
        }
        return vaiPara;
    }

        @PostMapping("acesso-prof")
    public ModelAndView acessoProf(@RequestParam String cpf,
            @RequestParam String senha) {
        ModelAndView mv = new ModelAndView();
        try {
            boolean verificaCpf = prof.existsById(cpf);
            boolean verificaSenha = prof.findByCpf(cpf).getSenha().equals(senha);
            String url = "";
            if (verificaCpf && verificaSenha) {
                acessoProf = true;
                url = "redirect:/interna-prof";
                mv.addObject("msg", "Logado com sucesso!");
                mv.setViewName(url);
            } else {
                url = "redirect:/login-prof";
                mv.addObject("msg", "Erro! Acesso negado ou senha incorreta.");
                mv.setViewName(url);
            }
            return mv;
        } catch (Exception e) {
            mv.addObject("msg", "Erro no login.");
            mv.setViewName("redirect:/login-prof");
            return mv;
        }

    }

}
