package api_review;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

// GET http://54.237.100.89:8000
public class SpartanDataTest {

    // we want all the test in this class use same base url same base path
    @BeforeClass
    public void setup(){
        baseURI = "http://54.237.100.89:8000";
        basePath = "/api" ;
    }

    // GET http://54.237.100.89:8000/api/spartans/{id}
    @Test
    public void TestOneSpartan(){

        // Send GET request to /spartans/{id} by providing valid id

        // verify the status code , content type , and actual data
        given()
                .pathParam("id", 12)
                .log().all()
                .accept(ContentType.JSON).  // telling the server you want json
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode( 200 )
                .contentType( ContentType.JSON )
                .body("id", equalTo(12) ) // "id" here is jsonpath to get id from response json
                .body("name" , is("Sol") )   // "name" here is jsonPath to get name from response json
        ;


    }




    // optionally reset above 2 values after you are done with this class
    @AfterClass
    public void cleanUp(){
        reset(); // it will set above 2 values in @BeforeClass to it's original value
    }



}
