package fr.jc.endpoints;

import fr.jc.entities.CompteUtilisateur;
import fr.jc.outils.EmailValidator;
import fr.jc.repositories.CompteUtilisateurRepository;
import fr.jc.security.PasswordHasher;
import jakarta.annotation.security.RolesAllowed;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Path("/comptes_utilisateurs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "COMPTE_UTILISATEUR", description = "Afficher la liste des comptes utilisateurs")
//@RolesAllowed("admin")
public class CompteUtilisateurEndpoints {
    @Inject
    CompteUtilisateurRepository compteUtilisateurRepository;

    @GET
    @Operation(summary = "Comptes utilisateurs", description = "get all user account")
    @APIResponse(responseCode = "200", description = "Ok, user account found")
    @APIResponse(responseCode = "204", description = "No user account !")
    public Response getAllComptesUtilisateurs() {
        List<CompteUtilisateur> comptesUtilisateurs = compteUtilisateurRepository.listAll();
        if (comptesUtilisateurs.isEmpty()) {
            return Response.noContent().build();
        } else {
            return Response.ok(comptesUtilisateurs).build();
        }
    }

    @GET
    @Operation(summary = "Compte utilisateur", description = "get one user account")
    @APIResponse(responseCode = "200", description = "Ok, user account found")
    @APIResponse(responseCode = "204", description = "No user account !")
    @Path("/{idCompteUtilisateur}")
    public Response getCompteUtilisateur(@PathParam("idCompteUtilisateur") Integer idCompteUtilisateur) {
        CompteUtilisateur compteUtilisateur = compteUtilisateurRepository.findById(idCompteUtilisateur);
        if (compteUtilisateur != null) {
            return Response.ok(compteUtilisateur).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Transactional
    @Operation(summary = "Création compte utilisateur", description = "post one user account")
    @APIResponse(responseCode = "200", description = "Ok, user account post")
    @APIResponse(responseCode = "204", description = "No user account post!")
    public Response createCompteUtilisateur(CompteUtilisateur compteUtilisateur, @Context HttpServletRequest request){
        // Vérification que le login est une adresse e-mail valide
        if (!EmailValidator.isValidEmail(compteUtilisateur.getLogin())) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Le login doit être une adresse e-mail valide.")
                    .build();
        }
        String hashedPassword = PasswordHasher.hashPassword(compteUtilisateur.getMotDePasse());
        compteUtilisateur.setMotDePasse(hashedPassword);
        LocalDateTime currentDateTime = compteUtilisateurRepository.getCurrentDateTime();
        compteUtilisateur.setDateCreationCompte(Timestamp.valueOf(currentDateTime).toLocalDateTime());
        // Récupérer l'adresse IP de la requête
        String ipConnexion = request.getRemoteAddr();
        // Assigner l'adresse IP au compte utilisateur
        compteUtilisateur.setIpConnexion(ipConnexion);
        // Récupérer le navigateur de la requête
        String navigateur = request.getHeader("User-Agent");
        // Assigner le navigateur au compte utilisateur
        compteUtilisateur.setNavigateur(navigateur);
        compteUtilisateurRepository.persist(compteUtilisateur);
        return Response.created(URI.create("/comptes_utilisateurs/" + compteUtilisateur.getIdCompteUtilisateur())).build();
    }

    @PUT
    @Transactional
    @Operation(summary = "Modification compte utilisateur", description = "update one user account")
    @APIResponse(responseCode = "200", description = "Ok, user account update")
    @APIResponse(responseCode = "204", description = "No user account update!")
    @Path("/{idCompteUtilisateur}")
    public Response updateCompteUtilisateur(@PathParam("idCompteUtilisateur") Integer idCompteUtilisateur, CompteUtilisateur updatedCompteUtilisateur){
        CompteUtilisateur compteUtilisateur = compteUtilisateurRepository.findById(idCompteUtilisateur);
        if(compteUtilisateur != null) {
            compteUtilisateur.setLogin(updatedCompteUtilisateur.getLogin());
            compteUtilisateur.setMotDePasse(updatedCompteUtilisateur.getMotDePasse());
            compteUtilisateur.setDateCreationCompte(updatedCompteUtilisateur.getDateCreationCompte());
            compteUtilisateur.setIpConnexion(updatedCompteUtilisateur.getIpConnexion());
            compteUtilisateur.setNavigateur(updatedCompteUtilisateur.getNavigateur());

            compteUtilisateurRepository.persist(compteUtilisateur);
            return Response.ok(compteUtilisateur).build();
        } else {
            return Response.noContent().build();
        }
    }

    @DELETE
    @Transactional
    @Operation(summary = "Suppression compte utilisateur", description = "delete one user account")
    @APIResponse(responseCode = "200", description = "Ok, user account delete")
    @APIResponse(responseCode = "204", description = "No user account delete!")
    @Path("/{idCompteUtilisateur}")
    public Response deleteCompteUtilisateur(@PathParam("idCompteUtilisateur") Integer idCompteUtilisateur) {
        CompteUtilisateur compteUtilisateur = compteUtilisateurRepository.findById(idCompteUtilisateur);
        if (compteUtilisateur != null) {
            compteUtilisateur.delete();

            return Response.ok(compteUtilisateur).build();
        } else {
            return Response.noContent().build();
        }
    }
}
