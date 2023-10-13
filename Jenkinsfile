pipeline {
     agent any
     stages {
        stage('Testing maven') {
             steps {
                 sh """mvn clean compile"""
             }
        }
     }
}
