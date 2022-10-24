package br.smartmanager.login;

import br.smartmanager.usuario.UsuarioResource;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("api/login")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioLoginService {

    @Inject
    UsuarioResource usuarioResource;

    @POST
    @PermitAll
    @Path("/auth")
    public Response login(@Valid UsuarioLogin uLogin) throws Exception{
        return usuarioResource.generateToken(uLogin);
    }
}
