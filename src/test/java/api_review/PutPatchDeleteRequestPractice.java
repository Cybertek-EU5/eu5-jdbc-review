package api_review;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

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

}
