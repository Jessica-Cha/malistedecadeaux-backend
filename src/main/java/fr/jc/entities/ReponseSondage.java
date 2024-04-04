package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "REPONSE_SONDAGE")
public class ReponseSondage extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_rep_sondage")
    private int idRepSondage;

    @Column(name = "lib_rep_sondage")
    private String libRepSondage;

    @Column(name = "date_rep_sondage")
    private Date dateRepSondage;

    @ManyToOne
    @JoinColumn(name = "id_rep_sugg")
    private ReponseSondage reponseSondage;

    @ManyToOne
    @JoinColumn(name = "id_sondage")
    private Sondage sondage;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_question")
    private QuestionSondage questionSondage;

}
