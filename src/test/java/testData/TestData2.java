package testData;

import java.util.HashMap;
import java.util.Map;

public class TestData2 {

    public Map<String, Object> bookingDatesSetUp(){

        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2022-07-24");
        bookingDates.put("checkout", "2022-07-28");
        return bookingDates;
    }

    public Map<String, Object> expectedDataSetUp(){

        Map<String, Object> expected = new HashMap<>();

        expected.put("firstname", "Martin");
        expected.put("lastname", "Matt");
        expected.put("totalprice", 456);
        expected.put("depositpaid", false);
        expected.put("bookingdates", bookingDatesSetUp());
        expected.put("additionalneeds", "extra ice");

        return expected;
    }
}
