package fr.jc.dto;


import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class UtilisateurDTO {
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String telephone;
    private boolean creationListe;
    private Integer idParent;
}
