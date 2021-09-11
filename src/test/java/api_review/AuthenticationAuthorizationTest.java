package api_review;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class AuthenticationAuthorizationTest {

    @BeforeClass
    public void setup(){
        baseURI = "http://54.237.100.89:7000";
        basePath = "/api" ;
    }

    @AfterClass
    public void teardown(){
        reset();  // this will reset above values in @BeforeClass to original value to avoid issues
    }

    @Test
    public void testPublicCanNotViewAnyData(){
        given()
                .log().all().
        when()
                .get("/spartans").
        then()
                .statusCode(401) ;
    }


    @Test
    public void testUserRoleCanViewAllData(){
        given()
                .auth().basic("user","user")
                .log().all().
        when()
                .get("/spartans").
        then()
                .statusCode(200) ;
    }

    @Test
    public void testUserRoleCannotDeleteData(){

        given()
                .auth().basic("user","user")
                .log().all()
                .pathParam("id", 123).
        when()
                .delete("/spartans/{id}").
        then()
                .log().all()
                .statusCode(403) ;
    }

}
