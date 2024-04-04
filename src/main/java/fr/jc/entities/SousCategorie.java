package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "SOUS_CATEGORIE")
public class SousCategorie extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_sous_categorie")
    private int idSousCategorie;

    @Column(name = "lib_sous_categorie")
    private String libSousCategorie;

    @ManyToOne
    @JoinColumn(name = "id_cat_produit")
    private CategorieProduit categorieProduit;

}
