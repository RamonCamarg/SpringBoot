package com.webapp.escola_xyz_b.Repository;


import org.springframework.data.repository.CrudRepository;

import com.webapp.escola_xyz_b.Model.Administrador;


public interface AdministradorRepository extends CrudRepository<Administrador, String> {
    //se eu precisar criar algum método extra eu vou criar aqui
    
    Administrador findByCpf(String cpf);
}
