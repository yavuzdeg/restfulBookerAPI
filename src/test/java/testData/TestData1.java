package testData;

import java.util.HashMap;
import java.util.Map;

public class TestData1 {

    public Map<String, Object> bookingDatesSetUp(){

        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2022-05-16");
        bookingDates.put("checkout", "2022-05-23");
        return bookingDates;
    }

    public Map<String, Object> expectedDataSetUp(){

        Map<String, Object> expected = new HashMap<>();

        expected.put("firstname", "Aaron");
        expected.put("lastname", "Green");
        expected.put("totalprice", 234);
        expected.put("depositpaid", true);
        expected.put("bookingdates", bookingDatesSetUp());
        expected.put("additionalneeds", "extra blanket");

        return expected;
    }
}
