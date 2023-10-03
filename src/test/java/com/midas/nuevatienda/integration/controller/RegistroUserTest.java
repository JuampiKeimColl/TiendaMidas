package com.midas.nuevatienda.integration.controller;

import com.midas.nuevatienda.controller.CodigoRespuestaEnum;
import com.midas.nuevatienda.integration.request.registrousuario.RegistroUsuarioRequestTest;
import com.midas.nuevatienda.integration.service.UsersPersistenceTestService;
import com.midas.nuevatienda.response.ClienteResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegistroUserTest extends TestController{

    @Autowired
    private RegistroUsuarioRequestTest registroUsuarioRequestTest;
    @Autowired
    private UsersPersistenceTestService usersPersistenceTestService;

    private final String CLIENTE_REGISTRO_USER_PATH = "/cliente/registroUser";
    private final String CLIENTE_REGISTRO_ADMIN_PATH = "/cliente/registroAdmin";

    @BeforeAll
    public void setup() {
        RestAssured.basePath = BASE_PATH;
    }

    @AfterEach
    public void afterEachTest(){
        usersPersistenceTestService.clearRegisters();
    }

    @Test
    void givenRegistroUser_whenPostRequest_thenStatusOk() {

        ClienteResponse response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(registroUsuarioRequestTest.createRequestOK())
                .when().log().all()
                .post(CLIENTE_REGISTRO_USER_PATH)
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .assertThat().body("codigoResultado", Matchers.equalTo(CodigoRespuestaEnum.OK.getCodigo()))
                .assertThat().body("descripcionResultado", Matchers.equalTo(CodigoRespuestaEnum.OK.getDescripcion()))
                .extract()
                .as(ClienteResponse.class);

        assertTrue(usersPersistenceTestService.findById(response.getClienteId()).isPresent());
    }

    @Test
    void givenRegistroAdmin_whenPostRequest_thenStatusOk() {

        ClienteResponse response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(registroUsuarioRequestTest.createRequestOK())
                .when().log().all()
                .post(CLIENTE_REGISTRO_ADMIN_PATH)
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .assertThat().body("codigoResultado", Matchers.equalTo(CodigoRespuestaEnum.OK.getCodigo()))
                .assertThat().body("descripcionResultado", Matchers.equalTo(CodigoRespuestaEnum.OK.getDescripcion()))
                .extract()
                .as(ClienteResponse.class);

        assertTrue(usersPersistenceTestService.findById(response.getClienteId()).isPresent());
    }

    @Test
    void givenRegistroUser_whenPasswordsDiferentes_thenStatusNot_Acceptable() {

        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(registroUsuarioRequestTest.createRequestPasswordsDiffError())
                .when().log().all()
                .post(CLIENTE_REGISTRO_ADMIN_PATH)
                .then().log().all()
                .statusCode(HttpStatus.NOT_ACCEPTABLE.value())
                .assertThat().body("codigoResultado", Matchers.equalTo(CodigoRespuestaEnum.ERROR_CONTRASENAS_DIFERENTES.getCodigo()))
                .assertThat().body("descripcionResultado", Matchers.equalTo(CodigoRespuestaEnum.ERROR_CONTRASENAS_DIFERENTES.getDescripcion()));
    }

    @Test
    void givenRegistroUser_whenNombreInvalido_thenStatusNot_Acceptable() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(registroUsuarioRequestTest.createRequestNameError())
                .when().log().all()
                .post(CLIENTE_REGISTRO_ADMIN_PATH)
                .then().log().all()
                .statusCode(HttpStatus.NOT_ACCEPTABLE.value())
                .assertThat().body("codigoResultado", Matchers.equalTo(CodigoRespuestaEnum.ERROR_NOMBRE_INVALIDO.getCodigo()))
                .assertThat().body("descripcionResultado", Matchers.equalTo(CodigoRespuestaEnum.ERROR_NOMBRE_INVALIDO.getDescripcion()));
    }

    @Test
    void givenRegistroUser_whenEmailInvalido_thenStatusNot_Acceptable() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(registroUsuarioRequestTest.createRequestEmailError())
                .when().log().all()
                .post(CLIENTE_REGISTRO_ADMIN_PATH)
                .then().log().all()
                .statusCode(HttpStatus.NOT_ACCEPTABLE.value())
                .assertThat().body("codigoResultado", Matchers.equalTo(CodigoRespuestaEnum.ERROR_EMAIL_INVALIDO.getCodigo()))
                .assertThat().body("descripcionResultado", Matchers.equalTo(CodigoRespuestaEnum.ERROR_EMAIL_INVALIDO.getDescripcion()));
    }
}
