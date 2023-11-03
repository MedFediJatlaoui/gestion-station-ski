pipeline {
     agent any
     stages {
        stage('Git') {
             steps {
                 git branch: 'sofiene_mazlout_5SAE4', url: 'https://github.com/MedFediJatlaoui/gestion-station-ski.git'
             }
        }
        stage('Maven') {
                   steps {
                     sh 'mvn clean install -Dmaven.test.skip=true'
                   }
                 }
     }

}
