package com.virgingames.myfirsttest;

import com.virgingames.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.parsing.Parser.JSON;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

public class TestFile extends TestBase {
    static ValidatableResponse response;

    @Test
    public void getData001() {

        RestAssured.registerParser("text/plain", JSON);  // Convert response from Text to Json Format
        response = given()
                .when()
                .get("")
                .then().log().all().statusCode(200);

        // To Verify StreamName is equal to "all_winners_vgn_daily" for Streams 7 record.
        response.body("bingoLobbyInfoResource.streams[7].streamName", equalTo("all_winners_vgn_daily"));

        // To Verify fullHouse value is not Null for Streams 0 record.
        response.body("bingoLobbyInfoResource.streams[0].fullHouse", notNullValue());

        // To Verify streamId is equal to "1253" for Streams 4 record.
        response.body("bingoLobbyInfoResource.streams[4].streamId", equalTo(1253));



    }
}