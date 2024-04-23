package fr.jc.endpoints;


import fr.jc.dto.UtilisateurDTO;
import fr.jc.entities.CompteUtilisateur;
import fr.jc.mapper.UtilisateurMapper;
import fr.jc.entities.Utilisateur;
import fr.jc.repositories.UtilisateurRepository;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("/utilisateurs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "UTILISATEUR", description = "Afficher la liste des utilisateurs")
//@RolesAllowed("admin")
public class UtilisateurEndpoints {


    @Inject
    UtilisateurRepository utilisateurRepository;

    @GET
    @Operation(summary = "Utilisateurs", description = "get all user")
    @APIResponse(responseCode = "200", description = "Ok, user found")
    @APIResponse(responseCode = "204", description = "No user !")
    public Response getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurRepository.listAll();
        if (utilisateurs.isEmpty()) {
            return Response.noContent().build();
        } else {
            List<UtilisateurDTO> utilisateursDTO = new ArrayList<>();
            // Initialiser les listes d'enfants pour éviter les erreurs de sérialisation
            for (Utilisateur utilisateur : utilisateurs) {
                utilisateur.getEnfants().size(); // Initialiser la liste d'enfants
                UtilisateurDTO utilisateurDTO = UtilisateurMapper.mapUtilisateurToDTO(utilisateur);
                utilisateursDTO.add(utilisateurDTO);
            }
            return Response.ok(utilisateursDTO).build();
        }
    }

    @GET
    @Operation(summary = "Utilisateur", description = "get one user")
    @APIResponse(responseCode = "200", description = "Ok, user found")
    @APIResponse(responseCode = "204", description = "No user !")
    @Path("/{idUtilisateur}")
    public Response getUtilisateur(@PathParam("idUtilisateur") Integer idUtilisateur) {
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur);
        if (utilisateur != null) {
            // Mapper l'utilisateur vers le DTO
            UtilisateurDTO utilisateurDTO = UtilisateurMapper.mapUtilisateurToDTO(utilisateur);
            // Retourner le DTO depuis l'API REST
            return Response.ok(utilisateurDTO).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Transactional
    @Operation(summary = "Création utilisateur", description = "post one user")
    @APIResponse(responseCode = "200", description = "Ok, user post")
    @APIResponse(responseCode = "204", description = "No user post!")
    public Response createUtilisateur(Utilisateur utilisateur, @Context HttpServletRequest request) {
        utilisateurRepository.persist(utilisateur);

        // Récupérer l'idUtilisateur
        int idUtilisateur = utilisateur.getIdUtilisateur();

        // Vérifier si l'utilisateur est parent
        if (utilisateur.isCreationListe()) {
            // Mettre à jour l'idParent avec l'idUtilisateur
            utilisateur.setIdParent(idUtilisateur);

            // Persister à nouveau l'utilisateur avec la mise à jour de idParent
            utilisateurRepository.persist(utilisateur);
        }
        return Response.created(URI.create("/utilisateurs/" + utilisateur.getIdUtilisateur())).build();
    }

  /*  @POST
    @Transactional
    @Operation(summary = "Création utilisateur enfant", description = "Crée un nouvel utilisateur enfant pour le parent connecté")
    @APIResponse(responseCode = "200", description = "Ok, utilisateur enfant créé")
    @APIResponse(responseCode = "400", description = "Bad Request")
    public Response createUtilisateurEnfant(Utilisateur utilisateurEnfant, @Context HttpServletRequest request) {
        // Authentification : Vérifier si le parent est connecté
        CompteUtilisateur compteUtilisateurParent = getCurrentUser(request);
        if (compteUtilisateurParent == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        // Récupération du parent à partir de son compte utilisateur
        Utilisateur parent = utilisateurRepository.findByCompteUtilisateur(compteUtilisateurParent);
        if (parent == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Parent not found").build();
        }

        // Attribution du parent à l'utilisateur enfant
        utilisateurEnfant.setParent(parent);

        // Enregistrement en base de données
        utilisateurRepository.persist(utilisateurEnfant);

        return Response.ok("Utilisateur enfant créé avec succès").build();
    }

    // Méthode utilitaire pour récupérer le compte utilisateur actuellement connecté
    private CompteUtilisateur getCurrentUser(HttpServletRequest request) {
        // Implémentez la logique pour récupérer le compte utilisateur actuellement connecté à partir de la requête HTTP
    }*/




    @PUT
    @Transactional
    @Operation(summary = "Modification utilisateur", description = "update one user")
    @APIResponse(responseCode = "200", description = "Ok, user update")
    @APIResponse(responseCode = "204", description = "No user update!")
    @Path("/{idUtilisateur}")
    public Response updateUtilisateur(@PathParam("idUtilisateur") Integer idUtilisateur, UtilisateurDTO updatedUtilisateurDTO){
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur);
        if(utilisateur != null) {
            utilisateur.setNom(updatedUtilisateurDTO.getNom());
            utilisateur.setPrenom(updatedUtilisateurDTO.getPrenom());
            utilisateur.setDateNaissance(updatedUtilisateurDTO.getDateNaissance());
            utilisateur.setTelephone(updatedUtilisateurDTO.getTelephone());
            utilisateur.setCreationListe(updatedUtilisateurDTO.isCreationListe());


            utilisateurRepository.persist(utilisateur);
            // Construire un objet DTO contenant uniquement les informations mises à jour de l'utilisateur
            UtilisateurDTO updatedDTO = UtilisateurMapper.mapUtilisateurToDTO(utilisateur);

            return Response.ok(updatedDTO).build();
        } else {
            return Response.noContent().build();
        }
    }

    @DELETE
    @Transactional
    @Operation(summary = "Suppression utilisateur", description = "delete one user")
    @APIResponse(responseCode = "200", description = "Ok, user delete")
    @APIResponse(responseCode = "204", description = "No user delete!")
    @Path("/{idUtilisateur}")
    public Response deleteUtilisateur(@PathParam("idUtilisateur") Integer idUtilisateur) {
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur);
        if (utilisateur != null) {
            utilisateurRepository.delete(utilisateur);

            return Response.ok().build();
        } else {
            return Response.noContent().build();
        }
    }
}
