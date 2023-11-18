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
        stage("Deploy image") {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: '521921d5-782a-4dad-b7e9-b4be707d7289', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                        sh "docker login -u ${DOCKERHUB_USERNAME} -p ${DOCKERHUB_PASSWORD}"
                        sh "docker push achrefbenmehrez/achrefbenmehrez_5sae4-g2-stationski"
                        sh "docker logout"
                    }
                }
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
                    testResultsAggregator columns: 'Health, Job, Status, Percentage, Total, Pass, Fail, Skip, Commits, LastRun, Duration, Description, Build', compareWithPreviousRun: true, jobs: [[jobFriendlyName: 'Station Ski', jobName: 'Pipeline station ski']], ignoreAbortedJobs: false, ignoreDisabledJobs: false, ignoreNotFoundJobs: false, ignoreRunningJobs: false, recipientsList: 'achrefpgm@gmail.com', sortresults: 'NAME', subject: 'Test Results', theme: 'light'
                    publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'html', reportFiles: 'index.html', reportName: 'HTML Report', useWrapperFileDirectly: true])
                }
            }
        }
    }
}