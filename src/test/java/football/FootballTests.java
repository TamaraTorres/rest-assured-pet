package football;

import config.FootballConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class FootballTests extends FootballConfig {
    @Test
    public void getDetailsOfOneArea(){
        given()
                .queryParam("areas",2076)
                .when()
                .get("/areas");
    }
    @Test
    public void getDetailsOfMultipleArea(){
        String areasId= "2076,2077,2080";
        given()
                .urlEncodingEnabled(false)
                .queryParam("areas",areasId)
                .when()
                .get("/areas");
    }
    @Test
    public void getDateFounded(){
        given()
                .when()
                .get("/teams/57")
                .then().body("founded",equalTo(1886));
    }
    @Test
    public void getFirstTeamName(){
        given()
                .when()
                .get("/competitions/2021/teams")
                .then()
                .body("teams.name[0]",equalTo("Arsenal FC"));
    }

    @Test
    public void getAllTeamData(){
        Response response =
                given()
                .when()
                .get("teams/57")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();
        String stringResponse = response.asString();
        assertNotNull("Response is Empty",stringResponse);

    }
    @Test
    public void extractHeaders(){
        Response response =
                given().get("teams/57")
                        .then().extract().response();
        String contentTypeHeader = response.getContentType();
        String apiVersionHeader = response.getHeader("X-API-version");
        assertEquals("v4",apiVersionHeader);
        assertTrue(contentTypeHeader.equals("application/json;charset=UTF-8"));
    }

    @Test
    public void extractAllTeamsNames(){
        Response response = given().get("competitions/2021/teams")
                .then().extract().response();
        List<String> teamNames = response.path("teams.names");
        int count =response.path("count");
        assertNotNull("List is empty",teamNames);
        assertEquals(count,20);
    }

    @Test
    public void extractFirstTeamName(){
        String nameTeam = given().get("competitions/2021/teams").jsonPath().getString("teams.name[0]");
        assertEquals("Arsenal FC",nameTeam);
    }

}
