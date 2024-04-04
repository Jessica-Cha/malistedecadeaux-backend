package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "TYPE_MODIFICATION")
public class TypeModification extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_type_modification")
    private int idTypeModification;

    @Column(name = "lib_type_modification")
    private String libTypeModification;

}
