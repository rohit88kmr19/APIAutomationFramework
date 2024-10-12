package org.example.Modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.example.pojos.*;

public class PayloadManager {

    Gson gson ;

public String createPayloadBookingAsString()
    {
        Booking booking = new Booking();
        booking.setFirstname("James");
        booking.setLastname("Brown");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);
        // Java Object -> JSON String (byteStream) - Serlization
        Gson gson = new Gson();
        String jsonStringpayload = gson.toJson(booking);
        System.out.println(jsonStringpayload);
        return jsonStringpayload;
    }

    public String createPayloadBookingAsStringFaker() {
        Faker faker = new Faker();
        Booking booking = new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(faker.random().nextInt(1000));
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);
        // Java Object -> JSON String (byteStream) - Serlization
        Gson gson = new Gson();
        String jsonStringpayload = gson.toJson(booking);
        System.out.println(jsonStringpayload);
        return jsonStringpayload;
    }

    public BookingResponse bookingResponseJava(String responseString) {
       Gson gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }

    //get Token

    public String setAuthPayload()
    {
        Auth auth = new Auth();
         auth.setUsername("admin");
         auth.setPassword("password123");
         Gson gson = new Gson();
         String jsonPayloadString = gson.toJson(auth); // Java Object -> JSON String (byteStream) - Serlization
        System.out.println("Payload set to the -->" +jsonPayloadString);
        return jsonPayloadString;
    }

        public String getTokenFromJSON(String tokenResponse)
        {
            Gson gson = new Gson();
            TokenResponse tokenResponse1= gson.fromJson(tokenResponse,TokenResponse.class);
            return tokenResponse1.getToken();
        }


    public Booking getResponseFromJSON(String getResponse) {

        Gson gson = new Gson();
        // Response ( JSON) ->  Object TokenResponse
        // Deserialization
        Booking booking = gson.fromJson(getResponse,Booking.class);
        return booking;

    }

    public String fullUpdatePayloadAsString() {
        Booking booking = new Booking();
        booking.setFirstname("Pramod");
        booking.setLastname("Dutta");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-05");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        return gson.toJson(booking);
    }
}
