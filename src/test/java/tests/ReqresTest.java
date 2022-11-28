package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ReqresTest {

    public static void main(String[] args) {
        baseURI = "https://reqres.in";

        // Create new user
        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(RequestBody.CreateUser())
                .when()
                .post("/api/users")
                .then()
                .log().all()
                .assertThat().statusCode(201)
                .extract()
                .response().asString();

        JsonPath js = new JsonPath(response);  // for parsing Json
        String userId = js.getString("id");

        System.out.println(userId);

        // Update the user
        given().log().all()
                .queryParam("id", userId)
                .header("Content-Type", "application/json")
                .body(RequestBody.UpdateUser())
                .when()
                .put("/api/users/")
                .then().assertThat().statusCode(200)
                .log().all();


        // Get our user data - Single user not found
        given().log().all()
                .queryParam("id", userId)
                .when()
                .get("/api/users/")
                .then().assertThat().statusCode(404)
                .log().all();

        // Get user data with userId = 2
        given().log().all()
                .when()
                .get("/api/users/2")
                .then().assertThat().statusCode(200)
                .log().all();

        // Delete our user
        given().log().all()
                .queryParam("id", userId)
                .when()
                .delete("/api/users/")
                .then().assertThat().statusCode(204)
                .log().all();
    }


            // with test annotation
    @Test
    public void getUserDataTest() {
        baseURI = "https://reqres.in";
        String response = given().log().all()
                .when()
                .get("/api/users/2")
                .then().assertThat().statusCode(200)
                .log().all()
                .extract()
                .response().asString();
    }




}

