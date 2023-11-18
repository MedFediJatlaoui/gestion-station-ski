pipeline {
    agent any

    stages {
        stage('Email') {
            steps {
                emailext body: 'testt', subject: 'starting jenkins', to: 'ahmedkaabar999@gmail.com'
            }
        }

        stage('Git') {
            steps {
                catchError(buildResult: 'FAILURE') {
                    git branch: 'ahmed_kaabar_5sae4', url: 'https://github.com/MedFediJatlaoui/gestion-station-ski.git'
                }
            }
        }

        stage('Maven clean/install') {
            steps {
                catchError(buildResult: 'FAILURE') {
                    sh 'mvn clean install -Dmaven.test.skip=true'
                }
            }
        }

        stage('Maven Compile') {
            steps {
                catchError(buildResult: 'FAILURE') {
                    sh 'mvn clean compile'
                }
            }
        }

        stage("MOCKITO") {
            steps {
                catchError(buildResult: 'FAILURE') {
                    sh "mvn test -Dtest=tn.esprit.spring.SubscriptionServicesImplMock"
                }
            }
        }

        stage("SONARQUBE") {
            steps {
                catchError(buildResult: 'FAILURE') {
                    sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password="
                }
            }
        }

        stage('Maven Nexus') {
            steps {
                catchError(buildResult: 'FAILURE') {
                    sh 'mvn deploy -DskipTests'
                }
            }
        }

        stage("Build Docker Image") {
            steps {
                catchError(buildResult: 'FAILURE') {
                    sh 'docker build -t ahmedkaabar/ahmed-kaabar-5sae4-g2-gestion-station-ski:latest .'
                }
            }
        }

        stage('Docker Push') {
            steps {
                catchError(buildResult: 'FAILURE') {
                    sh 'docker login -u ahmedkaabar -p azerty123'
                    sh 'docker push ahmedkaabar/ahmed-kaabar-5sae4-g2-gestion-station-ski:latest'
                }
            }
        }

        stage('Docker Compose') {
            steps {
                catchError(buildResult: 'FAILURE') {
                    sh 'docker compose up -d'
                }
            }
        }
    }

    post {
        failure {
            script {
                def errorMessage = "The build failed. Please check the Jenkins console output for details."
                currentBuild.result = 'FAILURE'
                emailext body: errorMessage, subject: 'Build Failure', to: 'ahmedkaabar999@gmail.com'
            }
        }
    }
}
