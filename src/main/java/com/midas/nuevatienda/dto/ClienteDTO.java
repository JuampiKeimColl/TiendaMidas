package com.midas.nuevatienda.dto;

import com.midas.nuevatienda.persistence.entity.OrdenCompra;
import com.midas.nuevatienda.persistence.entity.enums.Rol;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class ClienteDTO {
    private Long clienteId;
    private String clienteName;
    private String email;
    private String password;
    private Rol rol;
    private List<OrdenCompra> ordenCompra = new java.util.ArrayList<>();


}
