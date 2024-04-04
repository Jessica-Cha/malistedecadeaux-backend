package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@Table(name = "PRODUIT")
public class Produit extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_produit")
    private int idProduit;

    @Column(name = "nom_produit")
    private String nomProduit;

    @Column(name = "descriptif_produit")
    private String descriptifProduit;

    @Column(name = "prix_produit", columnDefinition = "MONEY")
    private BigDecimal prixProduit;

    @Column(name = "lien_affiliation")
    private String lienAffiliation;

    @ManyToOne
    @JoinColumn(name = "id_partenaire")
    private Partenaire partenaire;

    @ManyToOne
    @JoinColumn(name = "id_sous_categorie")
    private SousCategorie sousCategorie;

}
