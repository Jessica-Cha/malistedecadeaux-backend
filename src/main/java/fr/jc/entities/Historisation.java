package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "HISTORISATION")
public class Historisation extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_historisation")
    private int idHistorisation;

    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    @ManyToOne
    @JoinColumn(name = "id_liste")
    private Liste liste;

    @ManyToOne
    @JoinColumn(name = "id_compte_utilisateur")
    private CompteUtilisateur compteUtilisateur;

    @ManyToOne
    @JoinColumn(name = "id_cagnotte")
    private Cagnotte cagnotte;

    @ManyToOne
    @JoinColumn(name = "id_transac")
    private TransacFin transacFin;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_sondage")
    private Sondage sondage;

    @ManyToOne
    @JoinColumn(name = "id_type_modification")
    private TypeModification typeModification;

}
