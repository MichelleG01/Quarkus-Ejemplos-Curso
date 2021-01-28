package org.curso;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GettingTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/prueba/mensaje")
          .then()
             .statusCode(200)
             .body(is("HOLA MUNDO"));
    }

}