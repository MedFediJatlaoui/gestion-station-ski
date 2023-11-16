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
                  stage("MOCKITO") {
                                steps {
                                  sh "mvn test -Dtest=tn.esprit.spring.Services.SkierServiceImpMock"
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
                    sh 'mvn  deploy -DskipTests'
                    }
                    }
            stage("build spring docker image") {
                    steps {
                      sh 'docker build -t sofienemaz/sofiene-mazlout-5sae4-g2-gestion-station-ski:latest --build-arg JAR_URL=http://192.168.56.2:8081/repository/maven-releases/tn/esprit/spring/gestion-station-ski/1.0/gestion-station-ski-1.0.jar .'

                    }

                  }
             /*stage("build Angular docker image") {
             test test aaa
                                steps {
                                       script {
                                 git branch: 'main', url: 'https://github.com/sofiene10/Angular.git'
                                 // Navigate to the Angular frontend project directory

                                     // Build the Angular frontend Docker image
                                     sh 'docker build -t sofienemaz/angular:latest -f Dockerfile .'

                                     }
                              } }*/

             stage('Docker Push'){
                          steps{
                              sh 'docker login -u sofienemaz -p 191JMT2362'
                              sh 'docker push  sofienemaz/sofiene-mazlout-5sae4-g2-gestion-station-ski'
                          }
                     }

              stage('Docker Compose') {
                      steps {
                        sh 'docker compose up -d'
                        sh 'docker compose logs gestion-station-ski'
                      }
                    }

     }

}

