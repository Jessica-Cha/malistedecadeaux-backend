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
@Table(name = "TRANSAC_FIN")
public class TransacFin extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_transac")
    private int idTransac;

    @Column(name = "montant_transaction", columnDefinition = "MONEY")
    private BigDecimal montantTransaction;

    @Column(name = "date_transaction")
    private LocalDateTime dateTransaction;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_cagnotte")
    private Cagnotte cagnotte;

}
