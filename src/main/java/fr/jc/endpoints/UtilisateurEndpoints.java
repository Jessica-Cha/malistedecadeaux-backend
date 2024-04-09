package fr.jc.endpoints;


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
            return Response.ok(utilisateurs).build();
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
            return Response.ok(utilisateur).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Transactional
    @Operation(summary = "Création utilisateur", description = "post one user")
    @APIResponse(responseCode = "200", description = "Ok, user post")
    @APIResponse(responseCode = "204", description = "No user post!")
    public Response createUtilisateur(Utilisateur utilisateur, @Context HttpServletRequest request){
        utilisateur.getNom();
        utilisateur.getPrenom();
        utilisateur.getDateNaissance();
        utilisateur.getTelephone();
        utilisateur.isCreationListe();

        utilisateurRepository.persist(utilisateur);
       return Response.created(URI.create("/utilisateurs/" + utilisateur.getIdUtilisateur())).build();
    }

    @PUT
    @Transactional
    @Operation(summary = "Modification utilisateur", description = "update one user")
    @APIResponse(responseCode = "200", description = "Ok, user update")
    @APIResponse(responseCode = "204", description = "No user update!")
    @Path("/{idUtilisateur}")
    public Response updateUtilisateur(@PathParam("idUtilisateur") Integer idUtilisateur, Utilisateur updatedUtilisateur){
        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur);
        if(utilisateur != null) {
            utilisateur.setNom(updatedUtilisateur.getNom());
            utilisateur.setPrenom(updatedUtilisateur.getPrenom());
            utilisateur.setDateNaissance(updatedUtilisateur.getDateNaissance());
            utilisateur.setTelephone(updatedUtilisateur.getTelephone());
            utilisateur.setCreationListe(updatedUtilisateur.isCreationListe());
            utilisateur.setIdUtilisateurEstEnfant(updatedUtilisateur.getIdUtilisateurEstEnfant());

            utilisateurRepository.persist(utilisateur);
            return Response.ok(utilisateur).build();
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
            utilisateur.delete();

            return Response.ok(utilisateur).build();
        } else {
            return Response.noContent().build();
        }
    }
}
