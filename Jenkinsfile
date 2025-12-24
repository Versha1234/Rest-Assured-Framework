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
    success {
      echo 'Flow complete: POST → READ → DELETE succeeded.'
    }
    always {
      archiveArtifacts artifacts: 'target/surefire-reports/**/*.xml', fingerprint: true
    }
  }
}
