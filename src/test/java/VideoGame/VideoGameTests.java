package VideoGame;

import config.VideoGameConfig;
import config.VideoGameEndpoints;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Test;
import serialization.VideoGame;
import static org.junit.Assert.*;

import static io.restassured.RestAssured.given;

public class VideoGameTests extends VideoGameConfig {
    @Test
    public void testVideoGameSerializationByJson(){
        VideoGame newVideo = new VideoGame("Shooter","TammyGame","2022-12-12",7,"Mature");
        given()
                .body(newVideo)
                .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then();
    }

    @Test
    public void testVideoSchemaJson(){
        given()
                .pathParam("videoGameId",3)
                .accept("application/json")
                .when()
                .get(VideoGameEndpoints.SINGLE_VIDEO_GAME)
                .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("validVideoJsonSchema.json"));
    }

    @Test
    public void convertJsonResponseToPOJO(){
        Response response = given()
                .pathParam("videoGameId",3)
                .when()
                .get(VideoGameEndpoints.SINGLE_VIDEO_GAME);
        VideoGame videoGameTetris = response.getBody().as(VideoGame.class);
        assertEquals("Tetris",videoGameTetris.getName());
        assertTrue(88 == videoGameTetris.getReviewScore());
        assertTrue(videoGameTetris.getCategory().equals("Puzzle"));
    }
}
