package webapp.escola_completo.Repository;

import org.springframework.data.repository.CrudRepository;

import webapp.escola_completo.Model.PreCadAdm;

public interface PreCadAdmRepository extends CrudRepository<PreCadAdm, String>{
    
    PreCadAdm findByCpf (String cpf);

}
