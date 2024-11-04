pipeline {
    agent any

    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Pulling...'
                git branch: 'Mariem', url: 'https://github.com/ikramdhib/StationSki5SAE2Devops.git', credentialsId: 'GitHub-PAT-Jenkins'
            }
        }

        stage('Build') {
            steps {
                echo 'Building with Maven...'
                sh 'mvn compile'
            }
        }

        stage('Testing ') {
            steps {
                echo 'Testing with Maven...'
                sh 'mvn test'
            }
        }


       stage('sonarQube') {
                   steps {
                     withCredentials([usernamePassword(credentialsId: 'Sonar_Credential', usernameVariable: 'SONAR_USER', passwordVariable: 'SONAR_PASS')]) {
                           sh 'mvn sonar:sonar -Dsonar.login=$SONAR_USER -Dsonar.password=$SONAR_PASS'
                       }
                   }
               }



           stage('Deploy SNAPSHOT') {
                      steps {
                              sh 'mvn deploy '
                      }
               }


       stage('Docker Build') {
                 steps {
                     echo 'Building Docker image...'
                     script {
                         sh 'docker build -t maryemsebei/managerstationski:1.0 .'
                     }
                 }
             }

             stage('Docker Repository Creation') {
                       steps {
                           withCredentials([usernamePassword(credentialsId: 'Docker-Credential', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                               script {
                                   echo 'Creating Docker repository if it does not exist...'
                                   // Create the Docker repository using the Docker Hub API
                                   sh '''
                                   curl -X POST -u $DOCKER_USERNAME:$DOCKER_PASSWORD https://hub.docker.com/v2/repositories/maryemsebei/managerstationski/
                                   '''
                               }
                           }
                       }
                   }

                   stage('Docker Push') {
                       steps {
                           withCredentials([usernamePassword(credentialsId: 'Docker-Credential', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                               script {
                                   echo 'Logging in to Docker Hub...'
                                   sh "echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin"
                                   echo 'Pushing Docker image...'
                                   sh 'docker push maryemsebei/managerstationski:1.0'
                               }
                           }
                       }
                   }




    }



    post {
        always {
            echo 'Cleaning up...'
            sh 'docker container prune -f'
            sh 'docker image prune -f'
        }
    }
}
