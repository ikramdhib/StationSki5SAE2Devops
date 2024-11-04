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
                         sh 'docker images' 
                     }
                 }
             }


            stage('Docker Push') {
                    steps {
                        withCredentials([usernamePassword(credentialsId: 'Docker-Credential', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                            script {
                                echo 'Checking if Docker repository exists...'
                                def response = sh(script: "curl -s -o /dev/null -w \"%{http_code}\" -u $DOCKER_USERNAME:$DOCKER_PASSWORD https://hub.docker.com/v2/repositories/${DOCKER_USERNAME}/managerstationski/", returnStdout: true).trim()
                                if (response == '404') {
                                    echo 'Repository does not exist, creating now...'
                                    sh "curl -s -X POST -u $DOCKER_USERNAME:$DOCKER_PASSWORD https://hub.docker.com/v2/repositories/${DOCKER_USERNAME}/managerstationski/"
                                } else {
                                    echo 'Repository exists.'
                                }

                                echo 'Logging in to Docker Hub...'
                                sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin'
                                echo 'Pushing Docker image...'
                                sh 'docker push mariemsebei/managerstationski:1.0'
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
