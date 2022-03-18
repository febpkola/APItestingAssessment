package apitests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import java.util.List;

import static io.restassured.RestAssured.*;

public class Base {
    static String reference = null;
    static String jsonString = null;
    static boolean validationStatus;
    static int status = 0;
    static String error = null;
    static List<String> errors = null;
    static List<String> warnings = null;
    static Response response;


    @BeforeClass
    public void setup(){
        baseURI = "https://assessment-api-loan.herokuapp.com";
        RestAssured.authentication = RestAssured.preemptive().basic("admin","password");
    }
}
