package test;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testBase.HerokuappBaseUrl;
import utils.JsonUtil;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class TestGetRequest04 extends HerokuappBaseUrl {

    // Please go to the each test method about explanation.

    @Test
    public void getRequest01(){

        // In this class, I use an example which puts the expected data into a map in this class.
        // I converted the actual data into a map by using GSON and used the Assert methods for assertion.


        /*
       When I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking/80
       And response body should be like:
            {
               "firstname": "Jane",
               "lastname": "Doe",
               "totalprice": 111,
               "depositpaid": true,
               "bookingdates": {
                  "checkin": "2018-01-01",
                  "checkout": "2019-01-01"
               "additionalneeds": "Extra pillows please"
                   }
             }
         */

        spec.pathParams("first", "booking", "second", 80);

        Map<String, String> bookingDates = new HashMap<>();

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        Map<String, Object> expectedDataMap = new HashMap<>();

        expectedDataMap.put("firstname", "Jane");
        expectedDataMap.put("lastname", "Doe");
        expectedDataMap.put("totalprice", 111);
        expectedDataMap.put("depositpaid", true);
        expectedDataMap.put("bookingdates", bookingDates);
        expectedDataMap.put("additionalneeds", "Extra pillows please");

        Response response = given().spec(spec).when().get("/{first}/{second}");

        response.prettyPrint();

        Map<String, Object> actualDataMap = response.as(HashMap.class);

        Assert.assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));

        Assert.assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));

        Assert.assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));

        Assert.assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));

        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"), ((Map)actualDataMap.get("bookingdates")).get("checkin"));

        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"), ((Map)actualDataMap.get("bookingdates")).get("checkout"));

        Assert.assertEquals(expectedDataMap.get("additionalneeds"), actualDataMap.get("additionalneeds"));

    }


    @Test
    public void getRequest02() {

        // In this class, I convert the Json data into Java by using objectmapper which is in the JsonUtil class in Utils package.
        // Then I used the Assert methods for assertion.


        /*
       When I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking/509
       Then Status code is 200
       And the response body should be like:
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

        spec.pathParams("first", "booking", "second", 509);

        String expected = "{\n" +
                "\"firstname\": \"Josh\",\n" +
                "\"lastname\": \"Allen\",\n" +
                "\"totalprice\": 111,\n" +
                "\"depositpaid\": true,\n" +
                "\"bookingdates\": {\n" +
                "\"checkin\": \"2018-01-01\",\n" +
                "\"checkout\": \"2019-01-01\"\n" +
                "},\n" +
                "\"additionalneeds\": \"super bowls\"\n" +
                "}";

        HashMap<String, Object> expectedData = JsonUtil.convertJsonToJava(expected, HashMap.class);

        Response response = given().spec(spec).when().get("/{first}/{second}");

        response.prettyPrint();

        HashMap<String, Object> actualData = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);

        Assert.assertEquals(expectedData.get("firstname"), actualData.get("firstname"));

        Assert.assertEquals(expectedData.get("lastname"), actualData.get("lastname"));

        Assert.assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));

        Assert.assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));

        Assert.assertEquals(expectedData.get("bookingdates.checkin"), actualData.get("bookingdates.checkin"));

        Assert.assertEquals(expectedData.get("bookingdates.checkout"), actualData.get("bookingdates.checkout"));

        Assert.assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));

    }

}
