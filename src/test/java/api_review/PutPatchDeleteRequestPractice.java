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

        given()
                .pathParam("id",1200)
                .contentType(ContentType.JSON)
                .body(bodyPojo)
                .log().all().
        when()
                .put("/spartans/{id}").
        then()
                .log().all()
                .statusCode(204) ;
    }


}
