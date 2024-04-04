package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "QUESTION_SONDAGE")
public class QuestionSondage extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_question")
    private int idQuestion;

    @Column(name = "intitule_question")
    private String intituleQuestion;

    @ManyToOne
    @JoinColumn(name = "id_sondage")
    private Sondage sondage;

}
