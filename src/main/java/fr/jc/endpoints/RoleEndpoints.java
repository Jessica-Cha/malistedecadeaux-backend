package fr.jc.endpoints;

import fr.jc.entities.Role;
import fr.jc.repositories.RoleRepository;
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

@Path("/roles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "ROLE", description = "Afficher la liste des roles")
public class RoleEndpoints {
    @Inject
    RoleRepository roleRepository;

    @GET
    @Operation(summary = "Roles", description = "get all role")
    @APIResponse(responseCode = "200", description = "Ok, role found")
    @APIResponse(responseCode = "204", description = "No role !")
    public Response getAllRoles() {
        List<Role> roles = roleRepository.listAll();
        if (roles.isEmpty()) {
            return Response.noContent().build();
        } else {
            return Response.ok(roles).build();
        }
    }

    @GET
    @Operation(summary = "Role", description = "get one role")
    @APIResponse(responseCode = "200", description = "Ok, role found")
    @APIResponse(responseCode = "204", description = "No role !")
    @Path("/{idRole}")
    public Response getRole(@PathParam("idRole") Integer idRole) {
        Role role = roleRepository.findById(idRole);
        if (role != null) {
            return Response.ok(role).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Transactional
    @Operation(summary = "Création role", description = "post one role")
    @APIResponse(responseCode = "200", description = "Ok, role post")
    @APIResponse(responseCode = "204", description = "No role post!")
    public Response createRole(Role role, @Context HttpServletRequest request){
        role.getTypeRole();
        roleRepository.persist(role);
        return Response.created(URI.create("/roles/" + role.getIdRole())).build();
    }

    @PUT
    @Transactional
    @Operation(summary = "Modification role", description = "update one role")
    @APIResponse(responseCode = "200", description = "Ok, role update")
    @APIResponse(responseCode = "204", description = "No role update!")
    @Path("/{idRole}")
    public Response updateRole(@PathParam("idRole") Integer idRole, Role updatedRole){
        Role role = roleRepository.findById(idRole);
        if(role != null) {
            role.setTypeRole(updatedRole.getTypeRole());

            roleRepository.persist(role);
            return Response.ok(role).build();
        } else {
            return Response.noContent().build();
        }
    }

    @DELETE
    @Transactional
    @Operation(summary = "Suppression role", description = "delete one role")
    @APIResponse(responseCode = "200", description = "Ok, role delete")
    @APIResponse(responseCode = "204", description = "No role delete!")
    @Path("/{idRole}")
    public Response deleteRole(@PathParam("idRole") Integer idRole) {
        Role role = roleRepository.findById(idRole);
        if (role != null) {
            role.delete();

            return Response.ok(role).build();
        } else {
            return Response.noContent().build();
        }
    }
}
