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

         stage('junit & mockito') {
            steps {
                sh 'mvn test'
            }
         }


        stage('sonar'){
           steps{
                echo "Sonar";
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
           }
        }




        stage('mvn package'){
          steps{
              echo "mvn package";
              sh 'mvn package -DskipTests=true'
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
                sh 'docker tag stationsky:latest malekbenrabah/stationsky:latest'

            }
         }

          stage('docker push'){
              steps{
                  script{
                      sh 'docker login -u "malekbenrabah" -p "sFDbd)NRiRyc;x8" docker.io'
                      sh 'docker push malekbenrabah/stationsky:latest'
                  }
              }
          }

          stage('docker compose') {
              steps{
                  sh 'docker compose up -d'
              }
          }

          stage('mail') {
              steps {
                  script {
                      currentBuild.result = currentBuild.currentResult
                      def buildStatus = currentBuild.currentResult
                      def recipientEmail = "malek.benrabah2@gmail.com"
                      def buildUrl = env.BUILD_URL
                      def buildName = env.JOB_NAME
                      def buildDate = new Date().format('yyyy-MM-dd HH:mm:ss')
                      def buildPercentage = currentBuild.duration * 100 / currentBuild.estimatedDuration
                      def buildTotal = currentBuild.durationString

                      emailext subject: "Rapport de construction - ${buildStatus}",
                          body: "Nom du pipeline : ${buildName}\n" +
                                "Date de construction : ${buildDate}\n" +
                                "Statut : ${buildStatus}\n" +
                                "Pourcentage d'exécution : ${buildPercentage}%\n" +
                                "Durée totale : ${buildTotal}\n" +
                                "Détails de la construction : ${buildUrl}\n\n",
                          to: recipientEmail,
                          attachLog: true,
                          attachmentsPattern: currentBuild.logFile
                  }
              }
          }


     }
}
