package fr.jc.endpoints;


import fr.jc.entities.Genre;
import fr.jc.repositories.GenreRepository;
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

@Path("/genres")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "GENRE", description = "Afficher la liste des genres")

public class GenreEndpoints {
    @Inject
    GenreRepository genreRepository;

    @GET
    @Operation(summary = "Genres", description = "get all gender")
    @APIResponse(responseCode = "200", description = "Ok, gender found")
    @APIResponse(responseCode = "204", description = "No gender !")
    public Response getAllGenres() {
        List<Genre> genres = genreRepository.listAll();
        if (genres.isEmpty()) {
            return Response.noContent().build();
        } else {
            return Response.ok(genres).build();
        }
    }

    @GET
    @Operation(summary = "Genre", description = "get one gender")
    @APIResponse(responseCode = "200", description = "Ok, gender found")
    @APIResponse(responseCode = "204", description = "No gender !")
    @Path("/{idGenre}")
    public Response getGenre(@PathParam("idGenre") Integer idGenre) {
        Genre genre = genreRepository.findById(idGenre);
        if (genre != null) {
            return Response.ok(genre).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Transactional
    @Operation(summary = "Création genre", description = "post one gender")
    @APIResponse(responseCode = "200", description = "Ok, gender post")
    @APIResponse(responseCode = "204", description = "No gender post!")
    public Response createGenre(Genre genre, @Context HttpServletRequest request){
        genre.getTypeGenre();
        genreRepository.persist(genre);
        return Response.created(URI.create("/genres/" + genre.getIdGenre())).build();
    }

    @PUT
    @Transactional
    @Operation(summary = "Modification genre", description = "update one gender")
    @APIResponse(responseCode = "200", description = "Ok, gender update")
    @APIResponse(responseCode = "204", description = "No gender update!")
    @Path("/{idGenre}")
    public Response updateGenre(@PathParam("idGenre") Integer idGenre, Genre updatedGenre){
        Genre genre = genreRepository.findById(idGenre);
        if(genre != null) {
            genre.setTypeGenre(updatedGenre.getTypeGenre());

            genreRepository.persist(genre);
            return Response.ok(genre).build();
        } else {
            return Response.noContent().build();
        }
    }

    @DELETE
    @Transactional
    @Operation(summary = "Suppression genre", description = "delete one gender")
    @APIResponse(responseCode = "200", description = "Ok, gender delete")
    @APIResponse(responseCode = "204", description = "No gender delete!")
    @Path("/{idGenre}")
    public Response deleteGenre(@PathParam("idGenre") Integer idGenre) {
        Genre genre = genreRepository.findById(idGenre);
        if (genre != null) {
            genre.delete();

            return Response.ok(genre).build();
        } else {
            return Response.noContent().build();
        }
    }
}
