pipeline {
  agent any
  tools {
    maven 'MAVEN'   // match your Jenkins tool name
    jdk 'JAVA'          // match your Jenkins tool name
  }
  options {
    timestamps()
    

  }
  stages {
    stage('Build - POST') {
      steps {
        bat 'mvn clean test -Dtest=postData'
      }
    }
    stage('Deploy - READ') {
      steps {
        bat 'mvn test -Dtest=readData'
      }
    }
    stage('Cleanup - DELETE') {
      steps {
        bat 'mvn test -Dtest=deleteData'
      }
    }
  }
 post {
  always {
    // Archive all reports
    archiveArtifacts artifacts: 'reports/TestReport-*.html', fingerprint: true

    // Publish the latest HTML report
    publishHTML([
      reportDir: 'Logs/reports',
      reportFiles: 'TestReport-*.html',
      reportName: 'Latest TestNG Report',
      allowMissing: true,
      keepAll: true,
      alwaysLinkToLastBuild: true
    ])
  }
}
}
