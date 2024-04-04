package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name = "GROUPE")
public class Groupe extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_groupe")
    private int idGroupe;

    @Column(name = "nom_groupe")
    private String nomGroupe;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

}
