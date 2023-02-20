package br.com.email_jax_ws.resources;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;

// import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
// import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
// import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

// import br.com.email_jax_ws.model.Usuario;

import br.com.email_jax_ws.dao.*;
import br.com.email_jax_ws.filters.Authorize;
import br.com.email_jax_ws.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Path("usuario")
public class UsuarioResource {

    private DAOUsuario _repositorio = new DAOUsuario();

    private final SecretKey CHAVE = Keys
            .hmacShaKeyFor("7f-j&amp;CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&amp;RNbDHUX6"
                    .getBytes(StandardCharsets.UTF_8));

    @POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postRegister(Usuario usuario) throws SQLException {
        if (_repositorio.pegarUsuarioEmail(usuario.getEmail()) != null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Email já utilizado").build();
        }
        try {
            _repositorio.criarUsuario(usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getSenha());
            return Response.status(Response.Status.CREATED).entity("Usuario Criado").build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postLogin(Usuario usuario) throws SQLException {

        try {
            Integer id = _repositorio.pegarUsuario(usuario.getEmail(), usuario.getSenha());
            if (id == null) {

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Usuário não encontrado").build();

            } else {
                // return Response.status(Response.Status.CREATED).entity(id).build();
                String jwtToken = Jwts.builder()
                        .setSubject(usuario.getEmail())
                        .setIssuer("localhost:8080")
                        .setIssuedAt(new Date())
                        .setExpiration(Date
                                .from(LocalDateTime.now().plusMinutes(15L).atZone(ZoneId.systemDefault()).toInstant()))
                        .signWith(CHAVE, SignatureAlgorithm.HS512)
                        .compact();

                Link link = Link.fromUriBuilder(
                        UriBuilder.fromUri("http://localhost:8080/")
                                .path("usuario"))
                        .type("GET").type("POST").type("PUT").type("DELETE").build();

                return Response.status(Response.Status.CREATED).entity(jwtToken).links(link).build();
            }

        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    @Authorize
    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postUpdate(Usuario usuario) {

        try {
            if (usuario.getNome() != "" && usuario.getNome() != null) {
                _repositorio.updateUsuarioNome(usuario.getId(), usuario.getNome());

            }

            if (usuario.getTelefone() != "" && usuario.getTelefone() != null) {
                _repositorio.updateUsuarioTelefone(usuario.getId(), usuario.getTelefone());
            }

            if (usuario.getSenha() != "" && usuario.getSenha() != null) {
                _repositorio.updateUsuarioSenha(usuario.getId(), usuario.getSenha());
            }

            return Response.status(Response.Status.OK).entity("Usuário Atualizado").build();

        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    @Authorize
    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {

        try {
            if (_repositorio.deletarUsuario(id)) {
                return Response.status(Response.Status.OK).entity("Usuário deletado com sucesso").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Usuário não foi deletado").build();
            }

        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
}