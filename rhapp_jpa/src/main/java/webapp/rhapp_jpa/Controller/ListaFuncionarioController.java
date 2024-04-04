package webapp.rhapp_jpa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import webapp.rhapp_jpa.Model.Funcionario;
import webapp.rhapp_jpa.Repository.FuncionarioRepository;

@Controller
public class ListaFuncionarioController {
    @Autowired
    private FuncionarioRepository fr;

    @GetMapping("/lista")
    public ModelAndView listarfuncionario() {
        ModelAndView mv = new ModelAndView("funcionario/listafuncionarios");
        mv.addObject("funcionarios", fr.findAll());
        return mv;
    }

    @GetMapping("/deletarfuncionario/{id}")
    public String deletarFuncionario(@PathVariable("id") long id) {
        fr.delete(fr.findById(id));
        return "redirect:/lista";
    }

    @GetMapping("/editarfuncionario/{id}")
    public ModelAndView abrireditarfuncionario(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("funcionario/editarfuncionario");
        mv.addObject("funcionario", fr.findById(id));
        return mv;
    }

    @PostMapping("/editarfuncionario/{id}")
    public String updateFuncionario(Funcionario funcionario) {
        fr.save(funcionario);
        return "redirect:/lista";
    }
}