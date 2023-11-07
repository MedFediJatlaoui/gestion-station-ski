pipeline {
    agent any
    stages {
        stage('GIT') {
            steps {
                script {
                    checkout([$class: 'GitSCM',
                        branches: [[name: 'Achref_BenMehrez_5SAE4']],
                        userRemoteConfigs: [[
                            url: 'https://github.com/MedFediJatlaoui/gestion-station-ski.git'
                        ]]
                    ])
                }
            }
        }
        stage('Maven Clean and Compile') {
            steps {
                sh ''' 
                    mvn clean compile
                    mvn package -DskipTests
                '''
            }
        }
        stage('Unit tests') {
            steps {
                sh "mvn test"
            }
        }
        stage('MVN SonarQube') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonarqube -Dorg.jenkinsci.plugins.durabletask.BourneShellScript.HEARTBEAT_CHECK_INTERVAL=86400"
            }
        }
        stage('Nexus') {
            steps {
                sh "mvn deploy -DskipTests"
            }
        }
        stage('Building image') {
            steps {
                sh "docker build -t achrefbenmehrez/achrefbenmehrez_5sae4-g2-stationski ."
            }
        }

        stage('Deploy image') {
            steps {
                sh '''
                docker login -u achrefbenmehrez -p achrefdevops
                docker push achrefbenmehrez/achrefbenmehrez_5sae4-g2-stationski
                '''
            }
        }
        stage('Docker compose') {
            steps {
                sh "docker compose up -d"
            }
        }
        stage ("Report"){
            steps {
                script {
                    testResultsAggregator columns: 'Job, Build, Status, Percentage, Total, Pass, Fail',
                      recipientsList: 'achrefpgm@gmail.com',
                      outOfDateResults: '10', 
                      sortresults: 'Job Name',
                      subject: 'Test Results'
                    	 jobs: [
                            // Group with 2 Jobs
                            [jobName: 'My CI Job1', jobFriendlyName: 'Job 1', groupName: 'TeamA'],
                            [jobName: 'My CI Job2', jobFriendlyName: 'Job 2', groupName: 'TeamA'],
                            // jobFriendlyName is optional
                            [jobName: 'My CI Job3', groupName: 'TeamB'],
                            [jobName: 'My CI Job4', groupName: 'TeamB'],
                            // No Groups, groupName is optional
                            [jobName: 'My CI Job6'],
                            [jobName: 'My CI Job7']
                        ]
                    publishHTML(target: [
                        allowMissing: true,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: './html',  // Use a relative path
                        reportFiles: 'index.html',
                        reportName: 'Results'
                    ])
                }
            }
        }
    }
}