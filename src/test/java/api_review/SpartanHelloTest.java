package api_review;


import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class SpartanHelloTest {

    // GET http://54.237.100.89:8000/api/hello
    @Test
    public void helloTest(){

     Response response =  get("http://54.237.100.89:8000/api/hello")  ;
     // get status code
        System.out.println("response.statusCode() = " + response.statusCode());
        // get response as text
        System.out.println("response.asString() = " + response.asString());
        // print the response
        response.prettyPrint() ;

        assertEquals( response.statusCode(), 200 ) ;
        assertEquals(response.asString() , "Hello from Sparta");

        // get and assert Content-Type header
        System.out.println("response.header(\"Content-Type\") = "
                + response.header("Content-Type"));

        assertEquals(response.header("Content-Type"), "text/plain;charset=UTF-8");

    }

    // GET http://54.237.100.89:8000/api/hello
    @Test
    public void HelloEndpointInMethodChain_Test(){

        // The power of rest assured comes in method chaining
        // given  some specification provided here like
                // url , parameters , body , header ,request log,  authentication
        // when  you send
                // GET POST PUT PATCH DELETE request to endpoint
        // then  verify
                // response log can go here
                // assertion goes here with hamcrest matchers
        given()
                .baseUri("http://54.237.100.89:8000")
                .basePath("/api")
                .log().all(). // this only log the request
        when()
                .get("/hello").  // .prettyPeek().
        then()
                .log().all()  // this only log the response
                .assertThat()
                .statusCode(200)
                .body( is("Hello from Sparta") )
                .header("Content-Type", is("text/plain;charset=UTF-8")  )
                ;


    }

}
