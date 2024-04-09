package fr.jc.repositories;

import fr.jc.entities.Genre;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GenreRepository implements PanacheRepositoryBase<Genre, Integer> {
    @Override
    public Genre findById(Integer idGenre){return findByIdOptional(idGenre).orElse(null);}

}
