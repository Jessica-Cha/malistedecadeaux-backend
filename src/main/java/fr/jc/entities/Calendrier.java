package fr.jc.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "CALENDRIER")
public class Calendrier extends PanacheEntityBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_calendrier")
    private int idCalendrier;

    @Column(name = "event_calendrier")
    private String eventCalendrier;

    @Column(name = "date_calendrier")
    private Date dateCalendrier;

}
