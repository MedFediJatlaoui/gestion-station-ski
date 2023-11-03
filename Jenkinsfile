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

        stage('maven Package'){
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

          stage('Nexus') {
             steps{
                 sh "mvn deploy -DskipTests"
             }
          }
     }
}
