package com.midas.nuevatienda.integration.request.registrousuario;

import com.github.javafaker.Faker;
import com.midas.nuevatienda.request.RegistroRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistroUsuarioRequestTest {
    private final Faker faker = new Faker();

    public RegistroRequest createRequestOK(){
        RegistroRequest request = new RegistroRequest();
        request.setName(faker.gameOfThrones().dragon());
        request.setEmail(faker.internet().emailAddress());
        request.setPassword("123");
        request.setPassword2("123");
        return request;
    }

    public RegistroRequest createRequestNameError(){
        RegistroRequest request = new RegistroRequest();
        request.setName(null);
        request.setEmail(faker.internet().emailAddress());
        request.setPassword("123");
        request.setPassword2("123");
        return request;
    }

    public RegistroRequest createRequestEmailError(){
        RegistroRequest request = new RegistroRequest();
        request.setName(faker.pokemon().name());
        request.setEmail("hola");
        request.setPassword("123");
        request.setPassword2("123");
        return request;
    }

    public RegistroRequest createRequestPasswordsDiffError(){
        RegistroRequest request = new RegistroRequest();
        request.setName(faker.pokemon().name());
        request.setEmail("hola");
        request.setPassword("123?1A");
        request.setPassword2("123qwer");
        return request;
    }
}
