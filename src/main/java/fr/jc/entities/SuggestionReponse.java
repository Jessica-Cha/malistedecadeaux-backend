package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "SUGGESTION_REPONSE")
public class SuggestionReponse extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_rep_sugg")
    private int idRepSugg;

    @Column(name = "lib_rep_sugg")
    private String libRepSugg;

    @ManyToOne
    @JoinColumn(name = "id_question")
    private QuestionSondage questionSondage;

}
