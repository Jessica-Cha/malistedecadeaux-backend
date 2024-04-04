package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "composer")
@IdClass(ComposerPK.class)
public class Composer extends PanacheEntityBase {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_groupe", referencedColumnName = "id_groupe", insertable = false, updatable = false)
    private Groupe groupe;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_utilisateur", referencedColumnName = "id_utilisateur", insertable = false, updatable = false)
    private Utilisateur utilisateur;

}
