package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatePojo;
import pojos.BookingPojo;
import testBase.HerokuappBaseUrl;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;


public class TestPojo1 extends HerokuappBaseUrl {

   // tests in this class are done with the help of the POJO classes (BookingPojo, BookingDatePojo)

    @Test
    public void getPojo01(){


     /*
    When I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking/110
    Then HTTP Status Code should be 200
    And response body should be:
    {
      "firstname": "John",
    "lastname": "Smith",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
    }

    (WARNING: This public API is used and changed all the time. Please check from Postman beforehand)

         */


        // Setting the URL
        spec.pathParams("first", "booking", "second", 110);

        // Setting the expected data (for the data, Pojo class is used here)
        BookingDatePojo bookingDatePojo = new BookingDatePojo("2018-01-01", "2019-01-01");

        BookingPojo expectedPojo = new BookingPojo("John", "Smith", 111, true, bookingDatePojo, "Breakfast");

        // Sending the request
        Response response = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");

        response.prettyPrint();

        // Asserting the output (GSON)
        BookingPojo actualPojo = response.as(BookingPojo.class);

        System.out.println(actualPojo);

        assertEquals(200, response.getStatusCode());

        assertEquals(expectedPojo.getFirstName(), actualPojo.getFirstName());

        assertEquals(expectedPojo.getLastName(), actualPojo.getLastName());

        assertEquals(expectedPojo.isDepositPaid(), actualPojo.isDepositPaid());

        assertEquals(expectedPojo.getBookingDates().getCheckIn(), actualPojo.getBookingDates().getCheckIn());

        assertEquals(expectedPojo.getBookingDates().getCheckOut(), actualPojo.getBookingDates().getCheckOut());

        assertEquals(expectedPojo.getAdditionalNeeds(), actualPojo.getAdditionalNeeds());

    }



    @Test
    public void getPojo02(){


     /*
    When I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking/2442
    Then HTTP Status Code should be 200
    And response body should be:
    {
      "firstname": "Josh",
    "lastname": "Allen",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "super bowls"
    }

    (WARNING: This public API is used and changed all the time. Please check from Postman beforehand)

         */


        // Setting the URL
        spec.pathParams("first", "booking", "second", 228);

        // Setting the expected data (for the data, Pojo class is used here)
        BookingDatePojo bookingDatePojo = new BookingDatePojo("2018-01-01", "2019-01-01");

        BookingPojo expectedPojo = new BookingPojo("John", "Smith", 111, true, bookingDatePojo, "Breakfast");

        // Sending the request
        Response response = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");

        response.prettyPrint();

        // Asserting the output (ObjectMapper - JsonUtil class from the utils package)
        BookingPojo actualPojo = JsonUtil.convertJsonToJava(response.asString(), BookingPojo.class);

        System.out.println(actualPojo);

        assertEquals(200, response.getStatusCode());

        assertEquals(expectedPojo.getFirstName(), actualPojo.getFirstName());

        assertEquals(expectedPojo.getLastName(), actualPojo.getLastName());

        assertEquals(expectedPojo.isDepositPaid(), actualPojo.isDepositPaid());

        assertEquals(expectedPojo.getBookingDates().getCheckIn(), actualPojo.getBookingDates().getCheckIn());

        assertEquals(expectedPojo.getBookingDates().getCheckOut(), actualPojo.getBookingDates().getCheckOut());

        assertEquals(expectedPojo.getAdditionalNeeds(), actualPojo.getAdditionalNeeds());

    }


}
