package webapp.escola_a.Repository;

import org.springframework.data.repository.CrudRepository;

import webapp.escola_a.Model.PreCadProf;

public interface PreCadProfRepository extends CrudRepository<PreCadProf, String>{
    PreCadProf findByCpf (String cpf);
}
