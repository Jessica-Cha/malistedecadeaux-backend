package fr.jc.repositories;

import fr.jc.entities.CompteUtilisateur;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;

@ApplicationScoped
public class CompteUtilisateurRepository implements PanacheRepositoryBase<CompteUtilisateur, Integer> {
@Override
    public CompteUtilisateur findById(Integer idCompteUtilisateur){return findByIdOptional(idCompteUtilisateur).orElse(null);}

    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}
