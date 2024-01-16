package com.gorest.testsuite;

import com.gorest.testbase.TestBase;

import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.hamcrest.core.IsIterableContaining.hasItems;


public class UserAssertionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void start() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("size()", equalTo(20));
    }
   // Verify the if the name of id = 5914053 is equal to ”Brahmdev Devar”

    @Test
    public void test002() {
        response.body("find{it.id == 5914053}.name", equalTo("Brahmdev Devar"));
    }

    //Check the single ‘Name’ in the Array list (Somu Bhat)
    @Test
    public void test003() {
        response.body("name", hasItem("Somu Bhat"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Dinesh Mehrotra, Aanjaneya Iyer, Chandini Prajapat )
    @Test
    public void test004() {
        response.body("name", hasItems("Heema Kaniyar", "Rahul Iyengar", "Tara Panicker"));
    }

    //5. Verify the email of userid = 5914064 is equal “deshpande_bhagvan_pres@terry-miller.test”
    @Test
    public void test005() {
        response.body("find{it.id == 5914064}.email", equalTo("deshpande_bhagvan_pres@terry-miller.test"));
    }

    //6. Verify the status is “Active” of user name is “Mani Banerjee”
    @Test
    public void test006() {
        response.body("find{it.name == 'Mani Banerjee'}.status", equalTo("active"));
    }

    //7. Verify the Gender = male
    @Test
    public void test007() {
        response.body("find{it.name == 'Pres. Rajiv Dubashi'}.gender", equalTo("male"));
    }
}
