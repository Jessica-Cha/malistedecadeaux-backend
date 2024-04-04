package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "EVENEMENT")
public class Evenement extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_evenement")
    private int idEvenement;

    @Column(name = "lib_evenement")
    private String libEvenement;

    @Column(name = "date_evenement")
    private Date dateEvenement;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_calendrier")
    private Calendrier calendrier;

}
