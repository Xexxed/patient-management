import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;

public class PatientIntegrationTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:4004";
    }

    // @Test
    @Test
    public void shouldReturnPatientsWithValidToken() {
        //AAA(Arrange, Act, Assert)
        //Arrange
        String loginPayload = """
                {
                    "email":"testuser@test.com",
                    "password":"password123"
                 }
                """;
        //Act
        String token = RestAssured
                .given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                //Assert
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("token");
        System.out.println("Generated Token: " + token);
        //Act
        RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/patients")
                .then()
                //Assert
                .statusCode(200)
                .body("patients", notNullValue())
                .log().all();
    }
}
