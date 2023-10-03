package com.midas.nuevatienda.response;

import com.midas.nuevatienda.persistence.entity.OrdenCompra;
import com.midas.nuevatienda.persistence.entity.enums.Rol;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "response endpoints: /registroUser - /registroAdmin")
public class ClienteResponse extends BaseResponse {
    private Long clienteId;
    private String clienteName;
    private String email;
    private String password;
    private Rol rol;
    private List<OrdenCompra> ordenCompra = new java.util.ArrayList<>();
}
