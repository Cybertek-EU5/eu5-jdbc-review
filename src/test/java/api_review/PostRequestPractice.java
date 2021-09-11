package api_review;

import io.restassured.http.ContentType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.SpartanNoID;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class PostRequestPractice {
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
    public void addOneSpartanTest(){
        //POST http://54.237.100.89:8000/api/spartans
        /**
         * {
         *     "name": "Mary",
         *     "gender": "Female",
         *     "phone": 1231231231
         * }
         */
        String strBody = "{\n" +
                            "    \"name\": \"Mary\",\n" +
                            "    \"gender\": \"Female\",\n" +
                            "    \"phone\": 1231231231\n" +
                            "} " ;
        given()
                .contentType(ContentType.JSON)
                .body(strBody)
                .log().body().
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201) ;

    }

    @Test
    public void addOneSpartanTestWithMap(){
        //POST http://54.237.100.89:8000/api/spartans
        /**
         * {
         *     "name": "Mary",
         *     "gender": "Female",
         *     "phone": 1231231231
         * }
         */
        Map<String,Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name","Mary") ;
        bodyMap.put("gender","Female") ;
        bodyMap.put("phone",1231231231L) ;
        System.out.println("bodyMap = " + bodyMap);

        given()
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .log().body().
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201) ;

    }

    @Test
    public void addSpartanWithPOJO(){
        // post body does not have id !! that's why we created new class
        SpartanNoID bodyPOJO = new SpartanNoID("Erhan", "Male",1231231231L);
        System.out.println("bodyPOJO = " + bodyPOJO);

        given()
                .contentType(ContentType.JSON)
                .body(bodyPOJO)
                .log().body().
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201) ;
    }



}
