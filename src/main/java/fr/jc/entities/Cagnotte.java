package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "CAGNOTTE")
public class Cagnotte extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_cagnotte")
    private int idCagnotte;

    @Column(name = "date_creation_cagnotte")
    private LocalDateTime dateCreationCagnotte;

    @Column(name = "url_cagnotte")
    private String urlCagnotte;

    @Column(name = "objet_cagnotte")
    private String objetCagnotte;

    @Column(name = "montant_participation", columnDefinition = "MONEY")
    private BigDecimal montantParticipation;

    @Column(name = "solde_total", columnDefinition = "MONEY")
    private BigDecimal soldeTotal;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_evenement")
    private Evenement evenement;

}
