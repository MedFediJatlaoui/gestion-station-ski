pipeline {
     agent any
     stages {
        stage('maven clean') {
             steps {
                 sh """mvn clean compile"""
             }
        }
	stage('maven compile'){
	     steps{
                sh """mvn compile"""
             }
        }
        stage('maventest Sonarqube'){
             steps{

                sh """mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonarqube """
                
             }
        }
     }
}

