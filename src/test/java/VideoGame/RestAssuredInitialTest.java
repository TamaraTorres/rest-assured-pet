package VideoGame;

import org.junit.Test;

import static io.restassured.RestAssured.*;
public class RestAssuredInitialTest {
    @Test
    public void myFirstTest() {
        given()
                .log().all()
                .when()
                .get("https://videogamedb.uk/api/videogame")
                .then()
                .log().all();

    }
}
