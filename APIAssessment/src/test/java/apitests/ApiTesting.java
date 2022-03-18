package apitests;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import jsondata.BankAccount;
import jsondata.LoanData;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

@Listeners(listeners.TestNGListeners.class)
public class ApiTesting extends Base {

    @Test
    public void validateUsingRestAssured() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber("1234567890");
        bankAccount.setBankName("MOLEWA_BANK");//ICONIC_BANK , MINIONS_BANK","MOLEWA_BANK","SCRUM_BANK"

        LoanData loanData = new LoanData();
        loanData.setBankAccount(bankAccount);
        loanData.setIdNumber("9901025391018");
        loanData.setName("Pontsho");
        loanData.setSurname("Molewa");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(loanData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        response = given().
                header("", "").
                contentType("application/json").
                accept("*/*").
                body(jsonString).log().all().
                when().
                post("/loans");
        response.prettyPrint();
        try {
            status = response.body().path("status");
        } catch (java.lang.NullPointerException ex) {
//            System.out.println("No status assigned");
        }

        try {
            error = response.body().path("error");
        } catch (java.lang.NullPointerException ex) {
//            System.out.println("No errors found");
        }


        reference = response.body().path("reference");

        errors = response.body().path("errors");
        warnings = response.body().path("warnings");
        if (status != 0) {
            System.out.println("Error message : " + error);
            Assert.fail(error);
        }

        if (errors.size() > 0) {
            for (int i = 0; i < errors.size(); i++) {
                System.out.println("Error message : " + (i + 1) + " : " + errors.get(i));
                if (errors.size() == 1) {
                    Assert.fail(errors.get(i));
                }
            }
            if (errors.size() > 1) {
                Assert.fail("Multiple errors encountered : " + errors);
            }
        }

        if (warnings.size() > 0) {
            for (int k = 0; k < warnings.size(); k++) {
                System.out.println("Warning message : " + warnings.get(k));
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(warnings.get(0), "","Please check warning message encountered");
                try {
                    softAssert.assertAll();
                } catch (Throwable e) {
                    System.err.println(e);
                }
            }
        }
        try {
            validationStatus = response.body().path("validationStatus");
            Assert.assertEquals(validationStatus, true, "Validation failed");
        } catch (java.lang.NullPointerException ex) {
            System.out.println("No validation status assigned hence the null pointer exception");
            ex.printStackTrace();
        }

    }

}
