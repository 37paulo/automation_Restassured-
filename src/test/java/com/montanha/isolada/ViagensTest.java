package com.montanha.isolada;

import com.montanha.factory.UsuarioDataFactory;
import com.montanha.factory.ViagemDataFactory;
import com.montanha.pojo.Usuario;
import com.montanha.pojo.Viagem;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Matchers.contains;

public class ViagensTest {

    private String token;


    @Before
    public void setUp(){
        //configuração restassured
        baseURI = "http://localhost";
        port = 8089;
        basePath = "/api";

        Usuario usuarioAdm = UsuarioDataFactory.criarUsuarioAdministrador();

        this.token  =  given()
                .contentType(ContentType.JSON)
                .body(usuarioAdm)
            .when()
                .post("/v1/auth")
            .then()
                .extract()
                .path("data.token");
    }

    @Test
    public void testUsuarioVazio() {


        Usuario usuariovazio = UsuarioDataFactory.criarUsuarioVazio();

        given()
                .contentType(ContentType.JSON)
                .body(usuariovazio)
            .when()
                .post("/v1/auth")
            .then()
                .assertThat()
                .statusCode(400)
                .body("errors", hasItems("Senha não pode ser vazia.", "Email não pode ser vazio."));

    }

    @Test
    public void testCadastroDeviagemValidaRetornaSucesso() throws IOException {


      Viagem viagemVaslida = ViagemDataFactory.viagemValida();

        given()
            .contentType(ContentType.JSON)
            .body(viagemVaslida)
                .header("Authorization", token)
        .when()
            .post("/v1/viagens")
        .then()
                .assertThat()
                .statusCode(201)
                .body("data.localDeDestino", equalTo("Rio de Janeiro"))
                .body("data.acompanhante", equalToIgnoringCase("nicolas"));
    }

    @Test
    public void testSemdestino() throws IOException {

        Viagem viagemsemdestino = ViagemDataFactory.viagemSemDestino();

        given()
            .contentType(ContentType.JSON)
            .body(viagemsemdestino)
            .header("Authorization", token)
        .when()
            .post("/v1/viagens")
        .then()
                .assertThat()
                .statusCode(400)
                .log()
                .all();


    }

}
