package fr.jc.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name="UTILISATEUR")
public class Utilisateur extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_utilisateur")
    private int idUtilisateur;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "date_naissance")
    private Date dateNaissance;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "creation_liste")
    private boolean creationListe;

    @Column(name = "id_utilisateur_est_enfant", insertable = false, updatable = false)
    private Integer idUtilisateurEstEnfant;

    @Column(name = "id_parent")
    private Integer idParent;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "id_genre")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "id_compte_utilisateur")
    private CompteUtilisateur compteUtilisateur;

    @ManyToOne
    @JoinColumn(name = "id_parent", insertable = false, updatable = false)
    @JsonBackReference // Indique que cette propriété est la propriété inverse de la relation parent-enfant
    private Utilisateur parent;

    //relation bidirectionnelle du parent vers ses enfants
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY) // Pour charger uniquement quand je le demande
    @JsonIgnoreProperties("enfants") // Ignorer la sérialisation de la liste des enfants
    private List<Utilisateur> enfants = new ArrayList<>();

}
