package api_review;

import io.restassured.http.ContentType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.SpartanNoID;

import static io.restassured.RestAssured.*;

public class PutPatchDeleteRequestPractice {

    @BeforeClass
    public void setup(){
        baseURI = "http://54.237.100.89:8000";
        basePath = "/api" ;
    }

    @AfterClass
    public void teardown(){
        reset();  // this will reset above values in @BeforeClass to original value to avoid issues
    }

    @Test
    public void testFullUpdateData(){
        SpartanNoID bodyPojo = new SpartanNoID("eu5","Male",1231231231L);

        // optionally get the id by sending request to get all and grab first id
        int firstId =   get("/spartans").path("id[0]") ;

        given()
                .pathParam("id",firstId)
                .contentType(ContentType.JSON)
                .body(bodyPojo)
                .log().all().
        when()
                .put("/spartans/{id}").
        then()
                .log().all()
                .statusCode(204) ;
    }


    @Test
    public void testPartialUpdateData(){

        // Update only phone number
        String updateBodyStr = "{ \"phone\": 1231231230 }" ;

        // optionally get the id by sending request to get all and grab first id
        int firstId =   get("/spartans").path("id[0]") ; // id[0] is json path to get first id

        given()
                .pathParam("id",firstId)
                .contentType(ContentType.JSON)
                .body(updateBodyStr)
                .log().all().
        when()
                .patch("/spartans/{id}").
        then()
                .log().all()
                .statusCode(204) ;
    }

    @Test
    public void deletePractice(){

        // optionally get the id by sending request to get all and grab first id
        int firstId =   get("/spartans").path("id[0]") ; // id[0] is json path to get first id

        given()
                .log().uri()
                .pathParam("id",firstId ).
        when()
                .delete("/spartans/{id}").
        then()
                .statusCode(204);

    }

}
