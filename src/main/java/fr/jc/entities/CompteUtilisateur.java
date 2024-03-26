package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "COMPTE_UTILISATEUR")
public class CompteUtilisateur extends PanacheEntityBase {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_compte_utilisateur")
    private Integer idCompteUtilisateur;
    @Column(name = "login")
    private String login;
    @Column(name = "mot_de_passe")
    private String motDePasse;
    @Column(name = "date_creation_compte")
    private LocalDateTime dateCreationCompte;
    @Column(name = "ip_connexion")
    private String ipConnexion;
    @Column(name = "navigateur")
    private String navigateur;

}
