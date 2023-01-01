package pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDatePojo {

    @JsonProperty("checkin")
    private String checkIn;

    @JsonProperty("checkout")
    private String checkOut;

    public String getCheckIn() {
        return checkIn;
    }
    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }
    public String getCheckOut() {
        return checkOut;
    }
    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public BookingDatePojo() {
    }

    public BookingDatePojo(String checkIn, String checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "BookingDatePojo{" +
                "checkIn='" + checkIn + '\'' +
                ", checkOut='" + checkOut + '\'' +
                '}';
    }
}