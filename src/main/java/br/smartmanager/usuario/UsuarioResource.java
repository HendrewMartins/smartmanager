package br.smartmanager.usuario;

import br.smartmanager.exception.MenssageNotFoundException;
import br.smartmanager.login.UsuarioLogin;
import br.smartmanager.usuario.repository.UsuarioRepository;
import br.smartmanager.util.PBKDF2Encoder;
import br.smartmanager.util.TokenUtils;
import br.smartmanager.util.dtos.Token;
import io.quarkus.panache.common.Page;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@RequestScoped
public class UsuarioResource {

    @Inject
    UsuarioRepository repository;

    @Inject
    PBKDF2Encoder encoder;

    @ConfigProperty(name = "br.smartmanager.jwt.duration") public Long duration;
    @ConfigProperty(name = "mp.jwt.verify.issuer") public String issuer;

    @Transactional
    public Usuario saveUsuario(Usuario usuario) {
        if (!usuarioExiste(usuario.getEmail())) {
            usuario.setSenha(encoder.encode(usuario.getSenha()));
            repository.persistAndFlush(usuario);
            return usuario;
        }
        return null;
    }

    @Transactional
    public Usuario updateUsuario(Long id, Usuario usuario) throws MenssageNotFoundException {
        Usuario update = getUsuarioById(id);
        update.setEmail(usuario.getEmail());
        update.setNome(usuario.getNome());
        update.setRoles(usuario.getRoles());
        update.setSenha(encoder.encode(usuario.getSenha()));
        return update;
    }

    @Transactional
    public void deleteUsuario(Long id) throws MenssageNotFoundException {
        repository.delete(getUsuarioById(id));
    }


    public List<Usuario> allUsuario() {
        return repository.listAll();
    }


    public Usuario getUsuarioById(Long id) throws MenssageNotFoundException {
        return repository.findByIdOptional(id)
                .orElseThrow(() -> new MenssageNotFoundException("Não foi possivel localizar o Id informado"));
    }


    public List<Usuario> getUsuarioByNome(String nome) throws MenssageNotFoundException {
        return repository.findByNome(nome);
    }

    public boolean usuarioExiste(String email) {
        return (repository.count("email", email) > 0);
    }


    public Long countUsuario(){
        return repository.count();
    }


    public List<Usuario> allUsuarioPagination(int pag, int quant) {
        return repository.findAll().page(Page.of(pag,quant)).list();
    }


    public Response generateToken(UsuarioLogin userLogin) throws Exception{

        Usuario usuario = repository.find("nomeAcesso", userLogin.getNomeAcesso()).firstResult();

        try {
            if (usuario != null && verifyCryptPassword(usuario, userLogin)){
                try {
                    return Response.ok(new Token(TokenUtils.generateToken(usuario.getEmail(), usuario.getRoles(), duration, issuer))).build();
                } catch (Exception e) {
                    return Response.status(Status.UNAUTHORIZED).build();
                }
            } else {
                return Response.status(Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean verifyCryptPassword(Usuario user, UsuarioLogin login){
        return user.getSenha().equals(encoder.encode(login.getSenha()));
    }

}
