package test;

import io.restassured.response.Response;
import org.junit.Test;
import testBase.HerokuappBaseUrl;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class TestGetRequest01 extends HerokuappBaseUrl {


    @Test
    public void getRequest01(){

        /*

    When we send a GET Request to the URL https://restful-booker.herokuapp.com/booking/220
    Then HTTP Status code should be 200
    And Status Line should be HTTP/1.1 200 OK
    And Response body contains “111”
	And Response body does not contain “Not Found”
	And Server is "Cowboy"

    */

        spec.pathParams("first", "booking", "second", 220);

        Response response = given().spec(spec).when().get("/{first}/{second}");

        response.prettyPrint();

        response.then().assertThat().statusCode(200).statusLine("HTTP/1.1 200 OK");

        assertTrue(response.asString().contains("111"));

        assertFalse(response.asString().contains("Not Found"));

        assertEquals(response.getHeader("Server"), "Cowboy");
    }


    @Test
    public void getRequest02() {

        /*

    When we send a GET Request to the URL https://restful-booker.herokuapp.com/booking/666
    Then HTTP Status code should be 404
    And Status Line should be HTTP/1.1 404 Not Found
    And Response body contains “Not Found”
	And Response body does not contain “111”
	And Server is "Cowboy"

    */

        spec.pathParams("first", "booking", "second", 666);

        Response response = given().spec(spec).when().get("/{first}/{second}");

        response.prettyPrint();

        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        assertTrue(response.asString().contains("Not Found"));

        assertFalse(response.asString().contains("111"));

        assertEquals(response.getHeader("Server"), "Cowboy");


    }


}
