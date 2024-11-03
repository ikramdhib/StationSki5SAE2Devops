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
                script {
                    sh 'docker build -t ikramdhibikram/stationski:1.0 .'
                }
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

                 stage('Run Docker Container') {
                            steps {
                                echo 'Running Docker container...'
                                script {
                                    // Stop and remove any existing container
                                    sh 'docker stop managerstation || true'
                                    sh 'docker rm managerstation || true'

                                    // Run the new container
                                    docker.image("managerstation:v1.0").run("-d --name managerstation -p 8089:8089")
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
