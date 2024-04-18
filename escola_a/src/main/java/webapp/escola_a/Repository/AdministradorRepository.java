package webapp.escola_a.Repository;

import org.springframework.data.repository.CrudRepository;

import webapp.escola_a.Model.Administrador;

public interface AdministradorRepository extends CrudRepository<Administrador, String>{
    Administrador findByCpf (String cpf);
}
