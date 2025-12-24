pipeline {
  agent any
  tools {
    maven 'Maven_3.9.6'   // match your Jenkins tool name
    jdk 'JDK_21'          // match your Jenkins tool name
  }
  options {
    timestamps()
    ansiColor('xterm')
  }
  stages {
    stage('Build - POST') {
      steps {
        sh 'mvn -v'
        sh 'mvn clean test -Dtest=postData'
      }
    }
    stage('Deploy - READ') {
      steps {
        sh 'mvn test -Dtest=readData'
      }
    }
    stage('Cleanup - DELETE') {
      steps {
        sh 'mvn test -Dtest=deleteData'
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