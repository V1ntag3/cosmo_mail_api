package br.com.email_jax_ws.resources;

import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.email_jax_ws.dao.*;
import br.com.email_jax_ws.filters.Authorize;
import br.com.email_jax_ws.model.Mensagem;
import br.com.email_jax_ws.model.Usuario;

@Path("mensagem")
public class MensagemResource {

    private DAOUsuario _repositorio = new DAOUsuario();
    private DAOMensagem _repositorio_men = new DAOMensagem();

    @Authorize
    @POST
    @Path("enviar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postEnviarMensagem(Mensagem msg) throws SQLException {
        Integer destinario_id = _repositorio.pegarUsuarioEmail(msg.getEmail_destinatario());
        if (destinario_id == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Destinatário não encontrado").build();
        }
        else{
            msg.setDestinatario_id(destinario_id);
            try {
                _repositorio_men.mandarMensagem(msg);
                return Response.status(Response.Status.OK).entity("Mensagem Enviada").build();
            } catch (Exception ex) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
            }
        }
       
    }
    @Authorize
    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMensagem(@PathParam("id") int id) throws SQLException {

            try {
                return Response.status(Response.Status.OK).entity(_repositorio_men.mensagem(id)).build();
            } catch (Exception ex) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
            }
       
    }
    @Authorize
    @GET
    @Path("enviadas/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMensagensEnviadas(@PathParam("id") int id) throws SQLException {
        String response = new String();
        response = "{\n \"mensagens\": [";
        if (_repositorio_men.mensagensEnviadas(id).size() != 0 ) {
           
            for (Mensagem mensagem : _repositorio_men.mensagensEnviadas(id) ) {
                response += "{\"id\" :"+ mensagem.getId() + ", \"assunto\": \""+mensagem.getAssunto()+"\",\"corpo\": \""+mensagem.getCorpo()+"\", \"data\": \" "+mensagem.getData()+"\",\"email_dest\": \""+mensagem.getEmail_destinatario()+"\",\"id_destinatario\": "+mensagem.getDestinatario_id()+",\"id_remetente\": "+mensagem.getRemetente_id()+"},";
            }
        }  
        response += "]}";
        try {
            return Response.status(Response.Status.OK).entity(response).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @Authorize
    @GET
    @Path("recebidas/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMensagensRecebidas(@PathParam("id") int id) throws SQLException {
        String response = new String();
        response = "{\n \"mensagens\": [";
        if (_repositorio_men.mensagensRecebidas(id).size() != 0 ) {
           
            for (Mensagem mensagem : _repositorio_men.mensagensRecebidas(id) ) {
                response += "{\"id\" :"+ mensagem.getId() + ", \"assunto\": \""+mensagem.getAssunto()+"\",\"corpo\": \""+mensagem.getCorpo()+"\", \"data\": \" "+mensagem.getData()+"\",\"email_dest\": \""+mensagem.getEmail_destinatario()+"\",\"id_destinatario\": "+mensagem.getDestinatario_id()+",\"id_remetente\": "+mensagem.getRemetente_id()+"},";
            }
        }  
        response += "]}";
        try {
            return Response.status(Response.Status.OK).entity(response).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @Authorize
    @POST
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(Usuario user) {
        try {
            for (Integer men : user.mensagens) {
                _repositorio_men.deletarMensagem(men);
            }
            return Response.status(Response.Status.OK).entity("Mensagen(s) Deletada(s)").build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
}