package fr.jc.repositories;

import fr.jc.entities.Utilisateur;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class UtilisateurRepository implements PanacheRepositoryBase<Utilisateur, Integer> {
    @Override
    public Utilisateur findById(Integer idUtilisateur){return findByIdOptional(idUtilisateur).orElse(null);}


}
