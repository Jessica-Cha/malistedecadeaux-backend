package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "SONDAGE")
public class Sondage extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_sondage")
    private int idSondage;

    @Column(name = "url_sondage")
    private String urlSondage;

    @Column(name = "date_creation_sondage")
    private Date dateCreationSondage;

    @Column(name = "titre_sondage")
    private String titreSondage;

    @Column(name = "description_sondage")
    private String descriptionSondage;

    @Column(name = "date_debut")
    private Date dateDebut;

    @Column(name = "date_fin")
    private Date dateFin;

    @Column(name = "nbre_reponses")
    private int nbreReponses;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_type_sondage")
    private TypeSondage typeSondage;

    @ManyToOne
    @JoinColumn(name = "id_statut_sondage")
    private StatutSondage statutSondage;

    @ManyToOne
    @JoinColumn(name = "id_evenement")
    private Evenement evenement;

}
