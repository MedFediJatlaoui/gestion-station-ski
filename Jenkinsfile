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

          stage('docker push'){
              steps{
                  script{
                      sh 'docker login -u "malekbenrabah" -p "sFDbd)NRiRyc;x8" docker.io'
                      sh 'docker tag stationsky:latest malekbenrabah/stationsky:latest'
                      sh 'docker push malekbenrabah/stationsky:latest'
                  }
              }
          }

          stage('docker compose') {
              steps{
                  sh 'docker-compose up -d'
              }
          }

     }
}
