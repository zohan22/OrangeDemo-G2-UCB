package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;

public class ReportManager {
    private static ExtentReports extentReport;
    private static String filePath = "";
    private static String reportName = "";

    private static final ConcurrentHashMap<Long, ExtentTest> extentTestMap = new ConcurrentHashMap<>();
    private static ReportManager instance;

    private ReportManager() throws Exception {
        createExtentReportInstance();
    }

    public static ReportManager getInstance() {
        if (instance == null) {
            synchronized (ReportManager.class) {
                if (instance == null) {
                    try {
                        if (filePath.isEmpty()) {
                            String rootPath = Paths.get("").toAbsolutePath().toString();
                            init(rootPath + File.separator + "test-output" + File.separator + "ExtentReport.html", "OrangeHRM Automation Suite");
                        }
                        instance = new ReportManager();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }

    public ExtentTest getTest() {
        return extentTestMap.get(Thread.currentThread().getId());
    }

    public ExtentTest startTest(String testName) {
        ExtentTest test = extentReport.createTest(testName);
        extentTestMap.put(Thread.currentThread().getId(), test);
        return test;
    }

    public void flush() {
        if (extentReport != null) {
            extentReport.flush();
        }
    }

    private void createExtentReportInstance() throws Exception {
        if (filePath.isEmpty()) {
            throw new Exception("You need to call Init method to create an ExtentReports Object");
        }

        createReportPath();
        extentReport = new ExtentReports();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(filePath);
        
        htmlReporter.config().setDocumentTitle("Automation Report - " + reportName);
        htmlReporter.config().setReportName(reportName);
        htmlReporter.config().setTheme(Theme.STANDARD); // Tema claro del proyecto previo
        htmlReporter.config().setEncoding("utf-8");

        extentReport.attachReporter(htmlReporter);
        
        extentReport.setSystemInfo("OS", System.getProperty("os.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReport.setSystemInfo("Environment", "QA Demo");
    }

    public static void createReportPath() {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    public static void init(String reportPath, String name) throws Exception {
        if (extentReport == null) {
            filePath = reportPath;
            reportName = name;
        } else {
            throw new Exception("ExtentReports is already initialized");
        }
    }
}