package br.com.email_jax_ws.resources;

import java.sql.SQLException;

// import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
// import javax.ws.rs.GET;
import javax.ws.rs.POST;
// import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// import br.com.email_jax_ws.model.Usuario;

import br.com.email_jax_ws.dao.*;
import br.com.email_jax_ws.model.Usuario;
@Path("usuario")
public class UsuarioResource {

    private DAOUsuario _repositorio = new DAOUsuario();
    


    @POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postRegister(Usuario usuario) throws SQLException
    {
        if(_repositorio.pegarUsuarioEmail(usuario.getEmail()) != null){
            return Response.status(Response.Status.NOT_FOUND).entity("Email já utilizado").build();
        }
        try{
            _repositorio.criarUsuario(usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getSenha());
            return Response.status(Response.Status.CREATED).entity("Usuario Criado").build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        } 
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postLogin(Usuario usuario) throws SQLException
    {
       
        try{
           Integer id = _repositorio.pegarUsuario(usuario.getEmail(), usuario.getSenha());
           if (id == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Usuário não encontrado").build();

           }else{
            return Response.status(Response.Status.CREATED).entity(id).build();
           }
           
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        } 
    }
    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postUpdate(Usuario usuario){
       
        try{
            if(usuario.getNome() != "" && usuario.getNome() != null){
                _repositorio.updateUsuarioNome(usuario.getId(), usuario.getNome());

            }
        
            if(usuario.getTelefone() != "" && usuario.getTelefone() != null){
                _repositorio.updateUsuarioTelefone(usuario.getId(), usuario.getTelefone());
            }

            if(usuario.getSenha() != "" && usuario.getSenha() != null){
                _repositorio.updateUsuarioSenha(usuario.getId(), usuario.getSenha());
            }

            return Response.status(Response.Status.OK).entity("Usuário Atualizado").build();
           
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        } 
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id)
    {
        
        try{
            if(_repositorio.deletarUsuario(id)){
                return Response.status(Response.Status.OK).entity("Usuário deletado com sucesso").build();
            }else{
                return Response.status(Response.Status.NOT_FOUND).entity("Usuário não foi deletado").build();
            }
            
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        } 
    }
}