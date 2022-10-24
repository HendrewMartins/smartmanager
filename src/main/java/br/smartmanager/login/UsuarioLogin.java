package br.smartmanager.login;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioLogin {
    private String nomeAcesso;
    private String senha;
}
