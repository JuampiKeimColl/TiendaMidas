package com.midas.tienda.persistence.entity;

import com.midas.tienda.enums.Rol;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Cliente {
    //Con esta configuración de Id se genera una cadena de texto alfanumérica única. Con la anotación @GeneratedValue
    //autogenera un valor y con el valor uuid, genera el valor único al momento en que el repositorio persista
    //la entidad, se le agrega también una estrategia alternativa para que nos aseguremos que ningún id se repita
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String clienteId;
    private String clienteName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<OrdenCompra> ordenCompra;


}
