package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static final String OUTPUT_FOLDER = "test-output/";
    private static final String timeStamp = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(new Date());
    private static final String FILE_NAME = "Automation_Assessment_Report_" + timeStamp + ".html";


    static ExtentReports reportSetup() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME);
        htmlReporter.config().setDocumentTitle("Automation Assessment");
        String image = "data:image/png;base64,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";
        htmlReporter.config().setReportName("<a target='_blank' href='https://mfsafrica.com/'><div style='border-radius: 9px; border:3px solid #15af9f; float:right; background:url("+ image +") #fff; background-repeat:no-repeat; background-size:contain; height:50px;width:146px; display:inline-block;'></div></a> <b class='label suite-start-time blue darken-3'>Automation Assessment Report</b> ");
        htmlReporter.config().setTimeStampFormat("dd MMM yyyy hh:mm:ss a");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setProtocol(Protocol.HTTPS);
        htmlReporter.config().setTheme(Theme.DARK);

        File directory = new File(OUTPUT_FOLDER);
        if (! directory.exists()){
            directory.mkdir();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setReportUsesManualConfiguration(true);

        extent.setSystemInfo("OS",System.getProperty("os.name"));
        extent.setSystemInfo("Host Name", "Automation Assessment" );
        extent.setSystemInfo("Environment","Automation Assessment");
        extent.setSystemInfo("Tester Name",System.getProperty("user.name"));
        extent.setSystemInfo("Report Name","Automation Assessment");

        return extent;
    }
}