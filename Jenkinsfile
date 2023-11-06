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
                sh "docker build -t achrefbenmehrez/AchrefBenMehrez_5SAE4-G2-stationski ."
            }
        }
        stage('Deploy image') {
            steps {
                sh '''
                docker login -u achrefbenmehrez -p achrefdevops
                docker push achrefbenmehrez/AchrefBenMehrez_5SAE4-G2-stationski
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
                    testResultsAggregator jobs:[[jobName: 'My CI Job1'], [jobName: 'My CI Job2'], [jobName: 'My CI Job3']]
                }
            }
        }
    }
}