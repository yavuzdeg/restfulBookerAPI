package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import testBase.HerokuappBaseUrl;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;


public class TestGetRequest03 extends HerokuappBaseUrl{

    // In this class, I use examples with "body" (Matchers) method, JsonPath, Assert methods, and Soft Assert methods.

    @Test
    public void getRequest01(){

        /*
       When I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking/6
       Then HTTP Status Code should be 200
       And response content type is “application/JSON”
       And response body should be like:
            {
               "firstname": "Mark",
               "lastname": "Smith",
               "totalprice": 445,
               "depositpaid": false,
               "bookingdates": {
                  "checkin": "2021-03-27",
                  "checkout": "2021-09-05"
               "additionalneeds": "Breakfast"
                   }
             }
         */

         spec.pathParams("first", "booking", "second", 6);

         Response response = given().spec(spec).when().get("/{first}/{second}");

         response.prettyPrint();

         response.
                 then().
                 assertThat().
                 statusCode(200).
                 contentType(ContentType.JSON).
                 body("firstname", equalTo("Mark"),
                         "lastname", equalTo("Smith"),
                         "totalprice", equalTo(445),
                         "depositpaid", equalTo(false),
                         "bookingdates.checkin", equalTo("2021-03-27"),
                         "bookingdates.checkout", equalTo("2021-09-05"),
                         "additionalneeds", equalTo("Breakfast"));

    }


    @Test
    public void getRequest02(){

        /*
       When I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking/456
       Then HTTP Status Code should be 200
       And response content type is “application/JSON”
       And response body should be like:
            {
               "firstname": "Josh",
               "lastname": "Allen",
               "totalprice": 111,
               "depositpaid": true,
               "bookingdates": {
                  "checkin": "2018-01-01",
                  "checkout": "2019-01-01"
               "additionalneeds": "super bowls"
                   }
             }
         */

        spec.pathParams("first", "booking", "second", 456);

        Response response = given().spec(spec).when().get("/{first}/{second}");

        response.prettyPrint();

        JsonPath json = response.jsonPath();

        assertEquals("Status code is not matching", 200, response.getStatusCode());

        assertEquals("Content type is not Json", "application/json; charset=utf-8", response.getContentType());

        assertEquals("Firstname is not matching", "Josh", json.getString("firstname"));

        assertTrue("Lastname is not matching", json.getString("lastname").equals("Allen"));

        assertTrue("Total price is not matching", json.getInt("totalprice")==111);

        assertTrue("Deposit paid is not matching", json.getBoolean("depositpaid"));

        assertEquals("Check in date is not matching", "2018-01-01", json.getString("bookingdates.checkin"));

        assertTrue("Checkout date is not matching", json.getString("bookingdates.checkout").equals("2019-01-01"));

        assertEquals("Additional needs is not matching", "super bowls", json.getString("additionalneeds"));

    }

    @Test
    public void getRequest03(){

        /*
       When I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking/678
       Then HTTP Status Code should be 200
       And response content type is “application/JSON”
       And response body should be like:
            {
               "firstname": "John",
               "lastname": "Smith",
               "totalprice": 111,
               "depositpaid": true,
               "bookingdates": {
                  "checkin": "2018-01-01",
                  "checkout": "2019-01-01"
               "additionalneeds": "Breakfast"
                   }
             }
         */

        spec.pathParams("first", "booking", "second", 678);

        Response response = given().spec(spec).when().get("/{first}/{second}");

        response.prettyPrint();

        JsonPath json = response.jsonPath();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(json.getString("firstname"),"John", "Firstname is not matching");

        softAssert.assertEquals(json.getString("lastname"),"Smith", "Lastname is not matching");

        softAssert.assertEquals(json.getInt("totalprice"), 111, "Total price is not matching");

        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2018-01-01", "Check in date is not matching");

        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2019-01-01", "Check out date is not matching");

        softAssert.assertEquals(json.getString("additionalneeds"),"Breakfast", "Additional needs is not matching");

        softAssert.assertAll();

    }
}
