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
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            emailext(
                subject: 'Build Report - ${currentBuild.fullDisplayName}',
                body: '''<h1>Build Report</h1>
                        <p>Build URL: ${BUILD_URL}</p>
                        <p>Full Report: ${JENKINS_URL}${JOB_URL}testReport</p>''',
                recipientProviders: [culprits(), requestor()],
                attachmentsPattern: '**/target/surefire-reports/*.xml'
            )
        }
    }
}
