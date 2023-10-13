package com.digital.util;

import com.digital.ui.runner.ParallelExecutionRunnerTest;

import java.util.List;

public class ParallelExecutionGradleCmd implements Runnable {

    String params, threadName;
    List<String> features;
    boolean cmdStatus = true;

    public ParallelExecutionGradleCmd(String params, String threadName, List<String> features){
        this.params = params;
        this.threadName = threadName;
        this.features = features;
    }

    /***
     * Runnable interface run method will be executed for every thread
     */
    @Override
    public void run() {
       String gradleCommand;
       for(int loopVar = 0;loopVar < features.size();loopVar++){
           String tempThreadName = threadName + "_" + (loopVar + 1);
           String tempParams = params.replaceAll("<THREAD_NAME>", tempThreadName);
           tempParams = tempParams.replaceAll("<FEATURE_FILE_PATH>", features.get(loopVar));

           if(System.getProperty("os.name").contains("Windows")){
               gradleCommand = String.format("gradlew.bat %s", tempParams);
               cmdStatus = CommonUtilities.executeCommand(new String[] {"cmd", "/c", gradleCommand}, false, true);
           }else{
               gradleCommand = String.format("./gradlew %s", tempParams);
               cmdStatus = CommonUtilities.executeCommand(new String[] {"sh", "-c", gradleCommand}, false, true);
           }

           ParallelExecutionRunnerTest.testResult &= cmdStatus;
       }
    }
}
