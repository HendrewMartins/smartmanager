package br.smartmanager.usuario;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usuarioId")
    private Integer id;


    @Column(name = "usucodart")
    private Long codigoArtia;


}
