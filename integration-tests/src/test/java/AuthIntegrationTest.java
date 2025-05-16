import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;

public class AuthIntegrationTest {

    @BeforeAll
    static void setUp(){
        RestAssured.baseURI="http://localhost:4004";

    }

    @Test
    public void shouldReturnOKWithValidToken(){
        //AAA(Arrange, Act, Assert)
        //Arrange
        String loginPayload= """
                {
                    "email":"testuser@test.com",
                    "password":"password123"
                 }
                """;
        //Act
        Response response= RestAssured
                .given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                //Assert
                .statusCode(200)
                .body("token",notNullValue())
                .extract().response();
        String token=response.jsonPath().getString("token");
        System.out.println(" Generated Token: "+token);
    }

}
