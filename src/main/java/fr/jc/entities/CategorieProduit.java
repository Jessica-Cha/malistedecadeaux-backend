package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "CATEGORIE_PRODUIT")
public class CategorieProduit extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_cat_produit")
    private int idCatProduit;

    @Column(name = "lib_cat_produit")
    private String libCatProduit;

}
