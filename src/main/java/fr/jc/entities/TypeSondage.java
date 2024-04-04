package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "TYPE_SONDAGE")
public class TypeSondage extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_type_sondage")
    private int idTypeSondage;

    @Column(name = "lib_type_sondage")
    private String libTypeSondage;

}
