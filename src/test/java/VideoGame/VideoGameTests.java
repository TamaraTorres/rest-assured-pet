package VideoGame;

import config.VideoGameConfig;
import config.VideoGameEndpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

import static io.restassured.RestAssured.given;

public class VideoGameTests extends VideoGameConfig {
    @Test
    public void getAllGames(){
        given().when().get(VideoGameEndpoints.ALL_VIDEO_GAMES).then().statusCode(200);
    }
    @Test
    public void getAllGameInvalidURI(){
        given().when().get("/videos").then().statusCode(404);
    }
    @Test
    public void getOneGame(){
        given().pathParam("videoGameId",3)
                .when().get(VideoGameEndpoints.SINGLE_VIDEO_GAME).then().statusCode(200);
    }
    @Test
    public void getOneGameWithValidation(){
        given().pathParam("videoGameId",3)
                .when().get(VideoGameEndpoints.SINGLE_VIDEO_GAME)
                .then().statusCode(200).body("name",equalTo("Tetris"));
    }
    @Test
    public void getOneGameWithJsonExtract(){
        Response gameResponse= given().pathParam("videoGameId",3)
                .when().get(VideoGameEndpoints.SINGLE_VIDEO_GAME)
                .then().contentType(ContentType.JSON).extract().response();
        System.out.println(gameResponse.asString());
    }
    @Test
    public void getOneGameNegativeScenario(){
        // Getting not exiting game
        given().pathParam("videoGameId",25)
                .when().get(VideoGameEndpoints.SINGLE_VIDEO_GAME)
                .then().statusCode(404);
    }
    @Test
    public void createNewGame(){
        String gameBodyJson = "{\n" +
                "  \"category\": \"Platform\",\n" +
                "  \"name\": \"Mario\",\n" +
                "  \"rating\": \"Mature\",\n" +
                "  \"releaseDate\": \"2022-05-04\",\n" +
                "  \"reviewScore\": 89\n" +
                "}";

        given()
                .body(gameBodyJson)
                .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then().statusCode(200);
    }
}
