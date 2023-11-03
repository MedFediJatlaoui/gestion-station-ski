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
        stage('maven Package'){
             steps{
                 sh """mvn package -DskipTests=true """
             }
        }
        stage('maven Install Package'){
             steps{
                 sh """mvn install -DskipTests=true """
             }
        }
        stage('maven Deploy Nexus'){
             steps{
                 sh """mvn deploy -DskipTests=true """
             }
        }
        stage('Docker image'){
             steps{
                 sh ''' docker login -u fediijat -p kopp92i11
                 docker build -t fediijat/gestionstationski . '''
             }
        }
        stage('Docker Push'){
             steps{
                 sh """ docker push  fediijat/gestionstationski """
             }
        }
        stage('Docker Push'){
           steps{
                         sh """ docker push  fediijat/gestionstationski """
                     }
                }
     }
}
