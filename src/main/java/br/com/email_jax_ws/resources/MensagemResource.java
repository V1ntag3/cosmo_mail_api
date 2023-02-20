package br.com.email_jax_ws.resources;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
        Integer remetente_id = _repositorio.pegarUsuarioEmail(msg.getEmail_destinatario());
        if (remetente_id != null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Destinatário não encontrado").build();
        }
        else{
            msg.setRemetente_id(remetente_id);
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
    @Path("enviadas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMensagensEnviadas(Usuario user) throws SQLException {
        try {
            return Response.status(Response.Status.OK)
                    .entity("{\n \"mensagens\":" + _repositorio_men.mensagensEnviadas(user.getId()) + "}").build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @Authorize
    @GET
    @Path("recebidas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMensagensRecebidas(Usuario user) throws SQLException {
        try {
            return Response.status(Response.Status.OK)
                    .entity("{\n \"mensagens\":" + _repositorio_men.mensagensRecebidas(user.getId()) + "}").build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @Authorize
    @DELETE
    @Path("delete/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(ArrayList<Integer> mensagens) {

        try {
            for (Integer men : mensagens) {
                _repositorio_men.deletarMensagem(men);
            }
            return Response.status(Response.Status.OK).entity("Mensagen(s) Deletada(s)").build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
}