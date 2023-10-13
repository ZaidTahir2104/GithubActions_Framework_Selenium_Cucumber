@Library('jenkins-shared-library') _

pipeline {

  options {
    ansiColor('xterm')
  }

  environment {
    registry = albUtils.getRegistry(registry_type)
    registryCredential = albUtils.getRegistryCredentials(registry_type)
    dockerfile           = 'Dockerfile'
    registryRepo         = "/${application_name.toLowerCase()}/${git_repo}"
    CommitId             = 'False'
    imageURI             = "${registry}${registryRepo}:${env.BUILD_NUMBER}"
    VeraAppid            = "${git_repo}-${env.BUILD_NUMBER}"
    VeraAppName          = "Feature Flag Application"
    AbortOnFail          = false
    CompliancePolicy     = 'warn'
    VulnerabilityPolicy  = 'warn'
    RemoveImageOnPublish = false
  }

tools{
     jdk 'jdk8'
 }

  agent { 
   label 'Jenkins_Agent2'
  }

  stages { 
   stage('maven build') {
      steps {
        sh 'chmod +x gradlew'
        sh './gradlew chromeHeadlessCucumberTest -Ptestdata="pharmacy"-"${Banner}"_"${Environment}" -PcucumberTags="@${Test_Type}"'
      }
    }
  }

  post {
    always{
      junit "target/reports/digital/*.xml"
      publishHTML (target: [
             allowMissing: false,
             alwaysLinkToLastBuild: false,
             keepAll: true,
             reportDir: "ExtentReports",
             reportFiles: 'ExecutionReport.html',
             reportName: "Extent Reports"
          ])
      publishHTML (target: [
             allowMissing: false,
             alwaysLinkToLastBuild: false,
             keepAll: true,
             reportDir: "cucumber-local-reports/cucumber-html-reports",
             reportFiles: 'overview-features.html',
             reportName: "Cucumber Reports"
          ]) 
      
      script {
            def props = readProperties  file: 'build.properties'
            currentBuild.displayName = "#" + "${env.BUILD_NUMBER} -" + branch_name
        }
    }
    failure {
      script {
        albNotify.emailNotify()
      }
    }
  }
}
