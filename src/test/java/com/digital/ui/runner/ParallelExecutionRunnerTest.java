package com.digital.ui.runner;

import com.digital.util.CommonUtilities;
import com.digital.util.ExcelUtilities;
import com.digital.util.ParallelExecutionGradleCmd;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelExecutionRunnerTest {
    public static boolean testResult = true;
    public static List<Long> processIds = new ArrayList<>();

    /***
     * Test which will trigger the threads in parallel
     * @throws Exception
     */
    @Test
    public void parallelExecutionTest() throws Exception {
        //String browserName = "chromeHeadless"; //Possible values - chromeHeadless, chrome
        String browserName;
        System.out.println("**********************************************************");
        String testdata = System.getProperty("testData");
        String suiteName = System.getProperty("suiteName");
        int threadCount = Integer.parseInt(System.getProperty("threadCount"));
        int scriptTimeout = Integer.parseInt(System.getProperty("timeout"));
        String analyticsFlag = System.getProperty("analytics");

        String xlFileName = System.getProperty("file");

        System.out.println("Testdata file selected: " + testdata);
        System.out.println("Suite selected for execution: " + suiteName);
        System.out.println("XL File Name Provided: " + xlFileName);
        System.out.println("Thread Count Provided: " + threadCount);

        if(suiteName.contains("Mobile")){
            browserName = "chromeSpooferHeadless";
        }else{
            browserName = "chromeHeadless";
            //browserName = "ffheadless";
        }

        if(testdata == null || testdata.isEmpty()){
            Assert.fail("Testdata value is not provided. So, terminating execution!!!");
        }

        if(xlFileName == null || xlFileName.isEmpty()){
            Assert.fail("Excel file name is not provided. So, terminating execution!!!");
        }

        if(suiteName == null || suiteName.isEmpty()){
            suiteName = "FullRegression";
        }
        System.out.println("**********************************************************");

        String reportsPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "reports" + File.separator + "digital";
        CommonUtilities.deleteDirectory(new File(reportsPath));
        CommonUtilities.deleteDirectory(new File(System.getProperty("user.dir") + File.separator + "cucumber-local-reports"));
        File extentReportDir = new File(System.getProperty("user.dir") + File.separator + "ExtentReports");
        CommonUtilities.deleteDirectory(extentReportDir);

        URL url = ParallelExecutionRunnerTest.class.getResource("../../../../../../../build/resources/test/ui/parallelFiles/" + xlFileName);
        File xlFile = Paths.get(url.toURI()).toFile();

        ExcelUtilities excelUtilities = new ExcelUtilities(xlFile);
        List<List<String>> executionPlanData = excelUtilities.readParallelExecutionData(suiteName.replaceAll("@", ""));

        if(executionPlanData.size() == 0){
            Assert.fail("No feature files are specified in the execution plan. So, terminating execution!!!");
        }

        if(executionPlanData.size() <= threadCount){
            threadCount = executionPlanData.size();
        }

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for(int loopVar = 0;loopVar < executionPlanData.size();loopVar++){
            List<String> featuresByThreadDataList = executionPlanData.get(loopVar);

            String threadName = "_thread_" + (loopVar + 1);

            String gradleCommandParams;
            if(suiteName.equalsIgnoreCase("FullRegression")){
                gradleCommandParams = String.format("%sCucumberTest -Ptestdata=%s -PthreadName=<THREAD_NAME> -PcucumberFeature=<FEATURE_FILE_PATH>", browserName, testdata);
            }else{
                gradleCommandParams = String.format("%sCucumberTest -Ptestdata=%s -PthreadName=<THREAD_NAME> -PcucumberFeature=<FEATURE_FILE_PATH> -PcucumberTags=%s -Panalytics=%s", browserName, testdata, suiteName,analyticsFlag);
            }

            Runnable task = new ParallelExecutionGradleCmd(gradleCommandParams, threadName, featuresByThreadDataList);
            executorService.submit(task);

            CommonUtilities.wait(5);
        }

        if(!CommonUtilities.waitUntilAllThreadsAreStarted(processIds)){
            System.err.println("There is some issue in triggering execution. Please go through error console for moore details. So, terminating threads...");
            executorService.shutdownNow();
            return;
        }

        if(!CommonUtilities.waitUntilAllThreadsAreRunning(processIds, scriptTimeout)){
            System.err.println("Threads are still running even after timeout of " + scriptTimeout + " mins. So, terminating all threads...");
            executorService.shutdownNow();
            testResult = false;
        }

        if(!testResult){
            Assert.fail("Test(s) are completed with errors/exceptions. Check report/console for more details!!");
        }else{
            System.out.println("Test(s) completed successfully!!!");
        }
    }
}
