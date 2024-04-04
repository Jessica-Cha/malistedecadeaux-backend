package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "LISTE")
public class Liste extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_liste")
    private int idListe;

    @Column(name = "date_creation_liste")
    private Date dateCreationListe;

    @Column(name = "url_liste")
    private String urlListe;

    @ManyToOne
    @JoinColumn(name = "id_evenement")
    private Evenement evenement;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

}
