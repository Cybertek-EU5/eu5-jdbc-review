package api_review;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JsonPathPractice {

    // extract the response data using path and jsonPath
    // GET /api/spartans/search
    // Search for nameContains ab , Male
    // save the response object and use JsonPath to extract values
    // save it into String , Map , List<Map>

    /**
     * {
     *     "content": [
     *         {
     *             "id": 70,
     *             "name": "Abbey",
     *             "gender": "Male",
     *             "phone": 1259516767
     *         },
     *         {
     *             "id": 96,
     *             "name": "Abie",
     *             "gender": "Male",
     *             "phone": 1788387111
     *         }
     *     ],
     *     "totalElement": 2
     * }
     */

    @Test
    public void testSpartans(){

       Response response = given()
                                    .baseUri("http://54.237.100.89:8000")
                                    .basePath("/api")
                                    .queryParam("nameContains","ab")
                                    .queryParam("gender", "Male").
                            when()
                                    .get("/spartans/search") ;
       // get the total elements and print it out

       int totalElement = response.path("totalElement") ;
        System.out.println("totalElement = " + totalElement);

        // get JsonPath object out of Response object
        JsonPath jPath = response.jsonPath();
        System.out.println("jPath.getInt(\"totalElement\") = "
                + jPath.getInt("totalElement"));

        // get the phone number of 2nd spartan
        System.out.println("response.path(\"content[1].phone\") = "
                + response.path("content[1].phone"));
        System.out.println("jPath.getLong(\"content[1].phone\") = "
                + jPath.getLong("content[1].phone"));

        // Store all phone numbers into List<Long>
        List<Long> allPhones = response.path("content.phone");
        System.out.println("allPhones = " + allPhones);

        List<Long> allPhonesJ =  jPath.getList("content.phone"); // , Long.class);
        System.out.println("allPhonesJ = " + allPhonesJ);

        // save the first json object in json Array into Map object
        Map<String,Object> firstJsonAsMap =  jPath.getMap("content[0]") ;
        System.out.println("firstJsonAsMap = " + firstJsonAsMap);

        // get all the result into List<Map>
        List<Map<String,Object>> allResultAsLstOfMap = jPath.getList("content") ;
        System.out.println("allResultAsLstOfMap = " + allResultAsLstOfMap);

        // from above map , get the name of second item
        System.out.println("allResultAsLstOfMap.get(1).get(\"name\") = "
                + allResultAsLstOfMap.get(1).get("name"));

    }

    @Test
    public void testHR(){

        //GET Http://54.237.100.89:1000/ords/hr/employees

    Response response =  given()
                                .baseUri("Http://54.237.100.89:1000")
                                .basePath("/ords/hr").
                        when()
                                .get("/employees");
        JsonPath jPath = response.jsonPath() ;

        // example: salary more than 5k get first name
        List<String> allGreaterThan5K =
                jPath.getList("items.findAll{ it.salary > 5000 }.first_name ") ;
        System.out.println("allGreaterThan5K = " + allGreaterThan5K);

        // find out all employee last name in department id 50
        List<String> allDep50Emp = jPath.getList("items.findAll{ it.department_id==50}.last_name") ;
        System.out.println("allDep50Emp = " + allDep50Emp);

    }



}
