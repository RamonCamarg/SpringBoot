package webapp.escola_a.Repository;

import org.springframework.data.repository.CrudRepository;

import webapp.escola_a.Model.Professor;

public interface ProfRepository extends CrudRepository<Professor, String>{
    Professor findByCpf (String cpf);
}
