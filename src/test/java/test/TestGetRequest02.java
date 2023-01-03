package test;

import io.restassured.response.Response;
import org.junit.Test;
import testBase.HerokuappBaseUrl;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;


public class TestGetRequest02 extends HerokuappBaseUrl{

    // In this class, I use examples with queryParam(s).

    @Test
    public void getRequest01(){

        /*
         When I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking
	  	 Then status code is 200
    	 Among the data there should be someone whose first name is “Mark” and last name is “Ericsson”

        */


        spec.pathParam("first", "booking").queryParam("firstname", "Xavier").queryParam("lastname", "Ortega");

        Response response = given().spec(spec).when().get("/{first}");

        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        assertTrue(response.asString().contains("bookingid"));

    }

    @Test
    public void getRequest02(){

        /*
         When I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking
         Among the data, there is not someone whose first name is “James” and last name is “Green”
	  	 And status code is 200

        */

        spec.pathParam("first", "booking").queryParams("firstname", "James", "lastname", "Green");

        Response response = given().spec(spec).when().get("/{first}");

        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        assertFalse(response.asString().contains("bookingid"));

    }


}

