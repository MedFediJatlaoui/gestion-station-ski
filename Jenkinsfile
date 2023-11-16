pipeline {
     agent any
     stages {
        stage('maven clean compile') {
             steps {
                 sh """mvn clean compile"""
             }
        }

         stage('Mockito test'){
                     steps{
                        sh """mvn test """
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
        stage('Install .m2 '){
             steps{
                 sh """mvn install  """
             }
        }
        stage('maven Deploy Nexus'){
             steps{
                 sh """mvn deploy  """
             }
        }
        stage('Docker image'){
             steps{
                 sh ''' docker login -u fediijat -p kopp92i11
                 docker build -t fediijat/mohamedfedijatlaoui-5sae4-g2-gestionstationski . '''
             }
        }
        stage('Docker Push'){
             steps{
                 sh """ docker push  fediijat/mohamedfedijatlaoui-5sae4-g2-gestionstationski """
             }
        }
       stage('Notification par e-mail') {
           steps {
               script {
                   testResultsAggregator columns: 'Health, Job, Status, Percentage, Total, Pass, Fail, Skip, Commits, LastRun, Duration, Description, Build', compareWithPreviousRun: true, jobs: [[jobFriendlyName: 'GestionStationSki', jobName: 'gestion-station-ski-5SAE4-G2']], ignoreAbortedJobs: false, ignoreDisabledJobs: false, ignoreNotFoundJobs: false, ignoreRunningJobs: false, recipientsList: 'achrefpgm@gmail.com', sortresults: 'NAME', subject: 'Test Results', theme: 'light'
                   publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'html', reportFiles: 'index.html', reportName: 'HTML Report', useWrapperFileDirectly: true])
              }
           }
       }



     }
}
