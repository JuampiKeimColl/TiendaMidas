package com.midas.nuevatienda.mapper;

import com.midas.nuevatienda.dto.ClienteDTO;
import com.midas.nuevatienda.persistence.entity.Cliente;
import com.midas.nuevatienda.persistence.entity.enums.Rol;
import com.midas.nuevatienda.request.RegistroRequest;
import com.midas.nuevatienda.response.ClienteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    @Mapping(source = "name",target="clienteName")
    Cliente toCliente(String name, String email, String password, Rol rol);

    @Mapping(source = "registroRequest.name",target="clienteName")
    Cliente toCliente(RegistroRequest registroRequest, Rol rol);

    ClienteDTO toClienteDTO(Cliente cliente);

    ClienteResponse toClienteResponse(ClienteDTO cliente);

}
