package org.example.actions.DDTVWO;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.actions.utils.UtilsExcel;
import org.testng.annotations.Test;

public class VwoLoginAPIDDT {

    RequestSpecification r2;
    ValidatableResponse vR2;
    Integer ID;
    Response res2;

    @Owner("Rohit")
    @Description("TC# -Verify the Login page by reading test data from Excel file")
    @Test(dataProvider = "getData", dataProviderClass = UtilsExcel.class)
    public void testVWOLogin(String email, String password) {
        System.out.println(" -- Login API Testing");
        System.out.println(email);
        System.out.println(password);


    }
}