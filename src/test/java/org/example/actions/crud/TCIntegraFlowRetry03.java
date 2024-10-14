package org.example.actions.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.example.actions.base.BaseTest;
import org.example.actions.listeners.RetryAnalyzer;
import org.example.actions.utils.PropertyReader;
import org.example.endpoints.APIConstants;
import org.example.pojos.Booking;
import org.example.pojos.BookingResponse;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Test(retryAnalyzer = RetryAnalyzer.class)
public class TCIntegraFlowRetry03 extends BaseTest {

    // Create A Booking and Create a Token
    // Delete the Booking
    // Get booking Verify (404)


    @Test(groups = "integration", priority = 1)
    @Owner("Rohit")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext its){

        its.setAttribute("token", getToken());
        requestSpecification
                .basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(Integer.parseInt(PropertyReader.readKey("booking.post.statuscode.success")));


        //Default Rest Assured
        validatableResponse.body("booking.firstname", Matchers.equalTo(PropertyReader.readKey("booking.post.firstname")));


        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        // AssertJ
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo(PropertyReader.readKey("booking.post.firstname"));
        its.setAttribute("bookingid",  bookingResponse.getBookingid());

        Assert.assertTrue(true);

    }

    @Test(groups = "integration", priority = 2)
    @Owner("Rohit")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingId(ITestContext its){

        //Booking Id
        Integer bookingid = (Integer) its.getAttribute("bookingid");


        // GET Req
        String basePathGET = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);
        response = RestAssured
                .given(requestSpecification)
                .when().get();
        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readKey("booking.get.firstname"));


    }

    @Test(groups = "integration", priority = 3)
    @Owner("Rohit")
    @Description("TC#INT1 - Step 3. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext its) {
        System.out.println("ID deleted successfully" +its.getAttribute("bookingid"));
        String token = (String) its.getAttribute("token");
        Assert.assertTrue(true);

        Integer bookingid = (Integer) its.getAttribute("bookingid");
        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathDELETE);

        requestSpecification.basePath(basePathDELETE).cookie("token", token);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);

    }
    }
