package com.midas.nuevatienda.request;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@Schema(description = "Payload RegistroUser - Endpoint /")
public class RegistroRequest {
    @NotEmpty
    @Parameter(description = "nombre del usuario",required = true,example = "Juan Pedro")
    private String name;
    @Email
    @NotEmpty
    @NonNull
    private String email;
    private String password;
    private String password2;
}
