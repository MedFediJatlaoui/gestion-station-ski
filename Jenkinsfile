pipeline {
     agent any
     stages {
        stage('mvn clean') {
             steps {
                echo "Maven Clean";
                 sh 'mvn clean';
             }
        }

        stage('mvn compile'){
            steps{
                echo "Maven Compile";
                sh 'mvn compile';
            }
        }

        stage('mvn package'){
          steps{
              echo "mvn package";
              sh 'mvn package -DskipTests=true'
          }
        }

        stage('sonar'){
           steps{
                echo "Sonar";
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
           }
        }

        stage('nexus') {
             steps{
                 sh "mvn deploy -DskipTests"
             }
        }

         stage('docker image') {
            steps{
                sh 'docker build -t stationsky .'
            }
         }

     }
}
