package fr.jc.repositories;

import fr.jc.entities.Role;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RoleRepository implements PanacheRepositoryBase<Role, Integer> {
    @Override
    public Role findById(Integer idRole){return findByIdOptional(idRole).orElse(null);}
}
