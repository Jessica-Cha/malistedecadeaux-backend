package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "PARTENAIRE")
public class Partenaire extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_partenaire")
    private int idPartenaire;

    @Column(name = "nom_partenaire")
    private String nomPartenaire;

    @Column(name = "url_partenaire")
    private String urlPartenaire;

}
