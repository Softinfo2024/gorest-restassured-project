package com.gorest.crudtest;

import com.gorest.models.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

    static String name = "Shraddha" + TestUtils.getRandomValue();
    static String email = "shri@gmail.com" + TestUtils.getRandomValue();
    static String gender = "Female";
    static String status = "Active";
    static int userId;
    static String token = "03fce5af5bfff118402a50a1961ef191f6ab065e46dcdf6f5d96ea18c9496704";

    @Test
    public void test001()
    {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        userPojo.setEmail(email);

        Response response =
                given()
                        .header("Authorization" ,"Bearer" + token)
                        .contentType(ContentType.JSON)
                        .when()
                        .body(userPojo)
                        .post();
        response.prettyPrint();
        response.then().statusCode(401);
    }

    @Test
    public void test002() {

        Response response = given()
                .header("Authorization","Bearer" + token)
                .when()
                .get("/users" + "/" + userId);
        response.then().statusCode(200);
    }

    @Test
    public void test003()
    {
        UserPojo userPojo = new UserPojo();
        userPojo.setGender("female");
        userPojo.setStatus(status);


        Response response =
                given().log().all()
                        .header("Authorization" ,"Bearer" + token)
                        .contentType(ContentType.JSON)
                        .when()
                        .body(userPojo)
                        .put("/users" + "/" + userId);

        response.prettyPrint();
        response.then().statusCode(404);
    }

    @Test
    public void test004() {

        Response response = given()
                .header("Authorization","Bearer" + token)
                .when()
                .delete("/users" + "/" + userId);
        response.then().statusCode(204);
        response.prettyPrint();

    }
}
