package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "COMMENTAIRE")
public class Commentaire extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_commentaire")
    private int idCommentaire;

    @Column(name = "commentaire")
    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_cadeau")
    private Cadeau cadeau;

}
