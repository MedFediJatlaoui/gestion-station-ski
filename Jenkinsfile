pipeline {
     agent any
     stages
     {
        stage('Git') {
             steps {
                 git branch: 'sofiene_mazlout_5SAE4', url: 'https://github.com/MedFediJatlaoui/gestion-station-ski.git'
             }
        }
        stage('Maven clean/install') {
                   steps {
                     sh 'mvn clean install -Dmaven.test.skip=true'
                   }
                 }
        stage('Maven Compile') {
                     steps {
                         sh 'mvn clean compile'
                     }
                 }
         stage("SONARQUBE") {
              steps {
               sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
              }
            }
            stage('Maven Nexus')
                    {
                    steps {
                    sh 'mvn  deploy -DskipTests  '
                    }
                    }
            stage("build docker image") {
                    steps {
                      sh 'docker build -t sofienemazl/sofiene-mazlout-5sae4-g2-gestion-station-ski.jar .'
                    }
                  }

     }

}
