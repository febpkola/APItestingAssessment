package listeners;

import apitests.Base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListeners extends Base implements ITestListener {
    private ExtentTest extentTest;

    private static ExtentReports extent = ExtentManager.reportSetup();

    @Override
    public void onTestStart(ITestResult result) {
        String testCaseName = splitCamelCaseWord(result.getName());
        extentTest = extent.createTest(testCaseName, "Test case to " + testCaseName);
        extentTest.log(Status.INFO, "Test case started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testCaseName = splitCamelCaseWord(result.getName());
        extentTest.log(Status.PASS, "Test case : " + testCaseName + " <b class='label start-time'>passed</b>");
        extentTest.log(Status.INFO, "Test case completed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testCaseName = splitCamelCaseWord(result.getName());
        extentTest.log(Status.FAIL, "Test case " + testCaseName + " <b class='label end-time'>failed</b>"); //to add name in extent report
        extentTest.log(Status.INFO, "Failure reason : " + result.getThrowable()); //to add error/exception in extent report
        extentTest.log(Status.INFO, "Test case completed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testCaseName = splitCamelCaseWord(result.getName());
        extentTest.log(Status.SKIP, "Test case : " + testCaseName + " <b class='label time-taken orange lighten-1 white-text'>skipped</b>");
        extentTest.log(Status.SKIP, "Skip reason: " + result.getThrowable()); //to add error/exception in extent report
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }

    //split method name to user friendly readable text
    public String splitCamelCaseWord(String camelCaseWord) {
        String[] words = camelCaseWord.split("(?=\\p{Upper})");
        StringBuilder stringBuilder = new StringBuilder();
        for (String strTemp : words) {
            stringBuilder.append(strTemp.substring(0, 1).toUpperCase() + strTemp.substring(1) + " ");
        }
        return stringBuilder.toString();
    }

}