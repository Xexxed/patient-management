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



    @Test
    public void shouldReturnUnauthorizedOnInvalidLogin(){
        //AAA(Arrange, Act, Assert)
        //Arrange
        String loginPayload= """
                {
                    "email":"invalid_testuser@test.com",
                    "password":"wrongpassword123"
                 }
                """;
        //Act
        RestAssured
                .given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                //Assert
                .statusCode(401);


    }
    @Test
    public void shouldReturnUnauthorizedOnInvalidToken(){
        //AAA(Arrange, Act, Assert)
        //Arrange
        String invalidToken="invalid_token";
        //Act
        RestAssured
                .given()
                .header("Authorization", "Bearer " + invalidToken)
                .when()
                .get("/auth/validate")
                .then()
                //Assert
                .statusCode(401);
    }

    @Test

    public void shouldReturnOKOnValidToken() {
        //AAA(Arrange, Act, Assert)
        //Arrange
        String loginPayload = """
                {
                    "email":"testuser@test.com",
                    "password":"password123"
                 }
                """;
        //Act
        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                //Assert
                .statusCode(200)
                .body("token", notNullValue())
                .extract().response();
        String token = response.jsonPath().getString("token");
        RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/auth/validate")
                .then()
                //Assert
                .statusCode(200);
    };

}
