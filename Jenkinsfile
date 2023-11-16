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
        stage('Report') {
            steps {
                script {
                    testResultsAggregator afterbody: '', beforebody: '', columns: 'Health, Job, Status, Percentage, Total, Pass, Fail, Skip, Commits, LastRun, Duration, Description, Packages, Files, Classes, Methods, Lines, Conditions, Sonar, Build', compareWithPreviousRun: true, data: [[groupName: '', jobs: [[jobFriendlyName: 'Station Ski', jobName: 'Pipeline station ski']]]], ignoreAbortedJobs: false, ignoreDisabledJobs: false, ignoreNotFoundJobs: false, ignoreRunningJobs: false, outOfDateResults: '', recipientsList: 'achrefpgm@gmail.com', recipientsListBCC: '', recipientsListCC: '', recipientsListIgnored: '', sortresults: 'NAME', subject: 'Test Results', theme: 'light'
                }
            }
        }
    }
}
