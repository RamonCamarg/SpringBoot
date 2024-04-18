package webapp.escola_a.Repository;

import org.springframework.data.repository.CrudRepository;

import webapp.escola_a.Model.PreCadAdm;

public interface PreCadAdmRepository extends CrudRepository<PreCadAdm, String>{
    PreCadAdm findByCpf (String cpf);
}
