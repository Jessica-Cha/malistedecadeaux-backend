package fr.jc.mapper;

import fr.jc.dto.UtilisateurDTO;
import fr.jc.entities.Utilisateur;

public class UtilisateurMapper {
    // Méthode qui convertit un objet Utilisateur en UtilisateurDTO
    public static UtilisateurDTO mapUtilisateurToDTO(Utilisateur utilisateur) {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setNom(utilisateur.getNom());
        utilisateurDTO.setPrenom(utilisateur.getPrenom());
        utilisateurDTO.setDateNaissance(utilisateur.getDateNaissance());
        utilisateurDTO.setTelephone(utilisateur.getTelephone());
        utilisateurDTO.setCreationListe(utilisateur.isCreationListe());
        utilisateurDTO.setIdParent(utilisateur.getIdParent());
        return utilisateurDTO;
    }
}
