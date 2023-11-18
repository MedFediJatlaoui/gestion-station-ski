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
                script {
                    try {
                        git ranch: 'ahmed_kaabar_5sae4', url: 'https://github.com/MedFediJatlaoui/gestion-station-ski.git'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw new RuntimeException("Git stage failed: ${e.message}")
                    }
                }
            }
        }

        stage('Maven clean/install') {
            steps {
                script {
                    try {
                        sh 'mvn clean install -Dmaven.test.skip=true'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw new RuntimeException("Maven clean/install stage failed: ${e.message}")
                    }
                }
            }
        }

        stage('Maven Compile') {
            steps {
                script {
                    try {
                        sh 'mvn clean compile'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw new RuntimeException("Maven Compile stage failed: ${e.message}")
                    }
                }
            }
        }

        stage("MOCKITO") {
            steps {
                script {
                    try {
                        sh "mvn test -Dtest=tn.esprit.spring.SubscriptionServicesImplMock"
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw new RuntimeException("MOCKITO stage failed: ${e.message}")
                    }
                }
            }
        }

        stage("SONARQUBE") {
            steps {
                script {
                    try {
                        sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw new RuntimeException("SONARQUBE stage failed: ${e.message}")
                    }
                }
            }
        }

        stage('Maven Nexus') {
            steps {
                script {
                    try {
                        sh 'mvn deploy -DskipTests'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw new RuntimeException("Maven Nexus stage failed: ${e.message}")
                    }
                }
            }
        }

        stage("Build Docker Image") {
            steps {
                script {
                    try {
                        sh 'docker build -t ahmedkaabar/ahmed-kaabar-5sae4-g2-gestion-station-ski:latest .'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw new RuntimeException("Build Docker Image stage failed: ${e.message}")
                    }
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    try {
                        sh 'docker login -u ahmedkaabar -p azerty123'
                        sh 'docker push ahmedkaabar/ahmed-kaabar-5sae4-g2-gestion-station-ski:latest'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw new RuntimeException("Docker Push stage failed: ${e.message}")
                    }
                }
            }
        }

        stage('Docker Compose') {
            steps {
                script {
                    try {
                        sh 'docker compose up -d'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw new RuntimeException("Docker Compose stage failed: ${e.message}")
                    }
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
