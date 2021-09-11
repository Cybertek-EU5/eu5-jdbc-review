package api_review;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.Spartan;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class SerializationDeSerializationTest {

    /**
     // GET http://54.237.100.89:8000/api/spartans/13
     // and save the result into map
     // also save the result into custom object type Spartan
     */

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
    public void test1(){

        //GET /spartans/13
       Response response =  given()
                                    .pathParam("id",13).
                            when()
                                    .get("/spartans/{id}") ;
        response.prettyPrint();
        JsonPath jp = response.jsonPath() ;
        Map<String,Object> resAsMap = jp.getMap("") ;
        System.out.println("resAsMap = " + resAsMap);

        // Save it into Spartan POJO (Plain old Java Object)

        Spartan sp1 = response.as(Spartan.class) ;
        System.out.println("sp1 = " + sp1);


    }

    @Test
    public void test2(){
        // GET http://54.237.100.89:8000/api/spartans


        Response response = get("/spartans") ;
        //response.prettyPrint() ;

        // save all the name into List<String>
        JsonPath jp = response.jsonPath();
        List<String> allNames =  jp.getList("name", String.class) ;
        //System.out.println("allNames = " + allNames);

        // store the result into List<Spartan>
        List<Spartan> allSpartanPojo = jp.getList("", Spartan.class) ;
        System.out.println("allSpartanPojo = " + allSpartanPojo);


    }


}
