package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testBase.HerokuappBaseUrl;
import testData.TestData1;
import testData.TestData2;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class TestPostRequest01 extends HerokuappBaseUrl {


    // In this class, I used the POST method with a test data class.
    // Each test method has its own test data class in testData package.
    // In the first test method, I used JsonPath, in the second I used GSON for converting Json data into Java map.
    // In both of them, assertion is made with Assert methods.


    @Test
    public void post01(){

         /*
    When
    I send POST Request to the Url https://restful-booker.herokuapp.com/booking
    with the request body:
    {
                "firstname": "Aaron",
                "lastname": "Green",
                "totalprice": 234,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2022-05-16",
                    "checkout": "2022-05-23"
                   },
                "additionalneeds": "extra blanket"
    }
    Then
    Status code is 200
    And response body should be like
    {
        "bookingid": XXX,
        "booking": {
                 "firstname": "Aaron",
                "lastname": "Green",
                "totalprice": 234,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2022-05-16",
                    "checkout": "2022-05-23"
                   },
                "additionalneeds": "extra blanket"
        }
    }
    */

        spec.pathParam("first", "booking");

        TestData1 expectedData = new TestData1();

        Response response = given().
                spec(spec).
                contentType(ContentType.JSON).
                body(expectedData.expectedDataSetUp()).
                when().
                post("/{first}");

        response.prettyPrint();

        JsonPath json = response.jsonPath();

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedData.expectedDataSetUp().get("firstname"), json.getString("booking.firstname"));
        Assert.assertEquals(expectedData.expectedDataSetUp().get("lastname"), json.getString("booking.lastname"));
        Assert.assertEquals(expectedData.expectedDataSetUp().get("totalprice"), json.getInt("booking.totalprice"));
        Assert.assertEquals(expectedData.expectedDataSetUp().get("depositpaid"), json.getBoolean("booking.depositpaid"));
        Assert.assertEquals(expectedData.bookingDatesSetUp().get("checkin"), json.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedData.bookingDatesSetUp().get("checkout"), json.getString("booking.bookingdates.checkout"));
        Assert.assertEquals(expectedData.expectedDataSetUp().get("additionalneeds"), json.getString("booking.additionalneeds"));

    }


    @Test
    public void post02(){

         /*
    When
    I send POST Request to the Url https://restful-booker.herokuapp.com/booking
    with the request body:
    {
                "firstname": "Martin",
                "lastname": "Matt",
                "totalprice": 456,
                "depositpaid": false,
                "bookingdates": {
                    "checkin": "2022-07-24",
                    "checkout": "2022-07-28"
                   },
                "additionalneeds": "extra ice"
    }
    Then
    Status code is 200
    And response body should be like
    {
        "bookingid": XXX,
        "booking": {
                 "firstname": "Martin",
                "lastname": "Matt",
                "totalprice": 456,
                "depositpaid": false,
                "bookingdates": {
                    "checkin": "2022-07-24",
                    "checkout": "2022-07-28"
                   },
                "additionalneeds": "extra ice"
        }
    }
    */

        spec.pathParam("first", "booking");

        TestData2 expectedData = new TestData2();

        Response response = given().
                spec(spec).
                contentType(ContentType.JSON).
                body(expectedData.expectedDataSetUp()).
                when().
                post("/{first}");

        response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedData.expectedDataSetUp().get("firstname"), ((Map)actualData.get("booking")).get("firstname"));
        Assert.assertEquals(expectedData.expectedDataSetUp().get("lastname"), ((Map)actualData.get("booking")).get("lastname"));
        Assert.assertEquals(expectedData.expectedDataSetUp().get("totalprice"), ((Map)actualData.get("booking")).get("totalprice"));
        Assert.assertEquals(expectedData.expectedDataSetUp().get("depositpaid"), ((Map)actualData.get("booking")).get("depositpaid"));
        Assert.assertEquals(expectedData.bookingDatesSetUp().get("checkin"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        Assert.assertEquals(expectedData.bookingDatesSetUp().get("checkout"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));
        Assert.assertEquals(expectedData.expectedDataSetUp().get("additionalneeds"), ((Map)actualData.get("booking")).get("additionalneeds"));

    }


}
