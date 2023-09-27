package com.midas.tienda.entities;

import com.midas.tienda.enums.Rol;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Client {
    //Con esta configuración de Id se genera una cadena de texto alfanumérica única. Con la anotación @GeneratedValue
    //autogenera un valor y con el valor uuid, genera el valor único al momento en que el repositorio persista
    //la entidad, se le agrega también una estrategia alternativa para que nos aseguremos que ningún id se repita
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String clientId;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client client = (Client) o;
        return clientId != null && Objects.equals(clientId, client.clientId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
