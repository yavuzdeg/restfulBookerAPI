package pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingPojo {

    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("totalprice")
    private int totalPrice;

    @JsonProperty("depositpaid")
    private boolean depositPaid;

    @JsonProperty("bookingdates")
    private BookingDatePojo bookingDates;

    @JsonProperty("additionalneeds")
    private String additionalNeeds;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() { return lastName;     }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    public boolean isDepositPaid() {
        return depositPaid;
    }
    public void setDepositPaid(boolean depositPaid) { this.depositPaid = depositPaid; }
    public BookingDatePojo getBookingDates() {
        return bookingDates;
    }
    public void setBookingDates(BookingDatePojo bookingDates) {
        this.bookingDates = bookingDates;
    }
    public String getAdditionalNeeds() {
        return additionalNeeds;
    }
    public void setAdditionalNeeds(String additionalNeeds) {
        this.additionalNeeds = additionalNeeds;
    }

    public BookingPojo() {
    }

    public BookingPojo(String firstName, String lastName, int totalPrice, boolean depositPaid, BookingDatePojo bookingDates, String additionalNeeds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.bookingDates = bookingDates;
        this.additionalNeeds = additionalNeeds;
    }

    @Override
    public String toString() {
        return "BookingPojo{" +
                "firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", totalprice=" + totalPrice +
                ", depositpaid=" + depositPaid +
                ", bookingdates=" + bookingDates +
                ", additionalneeds=" + additionalNeeds +
                '}';
    }
}