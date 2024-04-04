package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "STATUT_SONDAGE")
public class StatutSondage extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_statut_sondage")
    private int idStatutSondage;

    @Column(name = "lib_statut_sondage")
    private String libStatutSondage;

}
