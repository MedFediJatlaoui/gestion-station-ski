pipeline {
     agent any
     stages
         {
            stage('Git') {
                 steps {
                git branch: 'ahmed_kaabar_5sae4', url: 'https://github.com/MedFediJatlaoui/gestion-station-ski.git'
                 }
            }
//             stage('Maven clean/install') {
//                        steps {
//                          sh 'mvn clean install -Dmaven.test.skip=true'
//                        }
//                      }
//             stage('Maven Compile') {
//                          steps {
//                              sh 'mvn clean compile'
//                          }
//                      }
//                       stage("MOCKITO") {
//                                     steps {
//                                       sh "mvn test -Dtest=tn.esprit.spring.Services.SubscriptionServicesImpl"
//                                     }
//                                   }
//              stage("SONARQUBE") {
//                   steps {
//                    sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar"
//                   }
//                 }
//
//                 stage('Maven Nexus')
//                         {
//                         steps {
//                         sh 'mvn  deploy -DskipTests'
//                         }
//                         }
}
