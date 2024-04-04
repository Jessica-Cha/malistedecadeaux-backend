package fr.jc.entities;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class ComposerPK implements Serializable {

    private Groupe groupe;
    private Utilisateur utilisateur;
}
