package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@Table(name = "CADEAU")
public class Cadeau extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_cadeau")
    private int idCadeau;

    @Column(name = "nom_cadeau")
    private String nomCadeau;

    @Column(name = "decription_cadeau")
    private String decriptionCadeau;

    @Column(name = "nom_image")
    private String nomImage;

    @Column(name = "url_site_marchand")
    private String urlSiteMarchand;

    @Column(name = "prix", columnDefinition = "MONEY")
    private BigDecimal prix;

    @ManyToOne
    @JoinColumn(name = "id_liste")
    private Liste liste;

}
