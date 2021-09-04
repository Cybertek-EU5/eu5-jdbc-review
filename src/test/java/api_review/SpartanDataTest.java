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

    /** This is the response we got
     * {
     *     "id": 12,
     *     "name": "Sol",
     *     "gender": "Male",
     *     "phone": 7006438852
     * }
     */
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
    // GET http://54.237.100.89:8000/api/spartans
    // specify we want json result back
    // verify the first item name is "Meade"
    // verify second item id equalTo 2'
    // verify the size of this array is

    /**
     * [
     *     {
     *         "id": 1,
     *         "name": "Meade",
     *         "gender": "Male",
     *         "phone": 3584128232
     *     },
     *     {
     *         "id": 2,
     *         "name": "Nels",
     *         "gender": "Male",
     *         "phone": 4218971348
     *     }
     *     the rest is omitted...
     *   ]
     */
    @Test
    public void testAllSpartans(){

        given()
                .accept(ContentType.JSON).
        when()
                .get("/spartans").
        then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                // name[0] is json path to get first json object name first
                .body("name[0]" , is("Meade"))
                .body("id[1]" , equalTo(2))
                // I want to verify the size of json array is 103
                // jsonPath to get your entire json array is "" empty string
                .body("" , hasSize(107) )
        ;

    }





    // optionally reset above 2 values after you are done with this class
    @AfterClass
    public void cleanUp(){
        reset(); // it will set above 2 values in @BeforeClass to it's original value
    }



}
