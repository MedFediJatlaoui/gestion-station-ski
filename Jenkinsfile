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
        stage('Docker COMPOSE'){
           steps{
                         sh """ docker compose up -d """
                     }
                }
        stage('Notification par e-mail') {
           steps {
              script {
               emailext body: '''<!DOCTYPE html>
                                 <html lang="fr">
                                 <head>
                                     <meta charset="UTF-8">
                                     <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                     <title>Rapport de build</title>
                                 </head>
                                 <body style="font-family: Arial, sans-serif; text-align: center; background-color: #f4f4f4; color: #333; padding: 20px;">

                                     <!-- Logo de Jenkins -->
                                     <img src="https://www.jenkins.io/images/logos/switzerland/switzerland.png" alt="Logo Jenkins" style="max-width: 200px; margin-bottom: 20px;">

                                     <!-- Message personnalisé -->
                                     <h2 style="color: #0074cc;">Rapport de build pour $PROJECT_NAME</h2>
                                     <p>Le build # $BUILD_NUMBER a été $BUILD_STATUS.</p>
                                     <p>Consultez la sortie de la console à l'adresse <a href="$BUILD_URL">$BUILD_URL</a> pour voir les résultats.</p>

                                     <!-- Pied de page -->
                                     <p style="margin-top: 30px; color: #888;">Cette notification a été générée automatiquement par Jenkins.</p>

                                 </body>
                                 </html> ''',
                        recipientProviders: [[$class: 'DevelopersRecipientProvider']],
                        subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS ',
                        to: 'jatlaouimedfedi@gmail.com'
                                }
                            }
        stage('Deployment K8s'){
           steps{
                        sh ''' kubectl apply -f mysql-service.yaml
                               kubectl apply -f service.yaml
                               kubectl apply -f deployment.yaml '''
                     }
                }

     }
}
