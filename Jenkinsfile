pipeline {
    agent any

    stages {
        stage('Pulling') {
            steps {
                echo 'Pulling...'
                git(
                    branch: 'Emna_labidi',
                    url: 'https://github.com/ikramdhib/StationSki5SAE2Devops.git',
                    credentialsId: 'github-pat'
                )
            }
        }

        stage('Build') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('Test') {
             steps {
                 sh 'mvn test'
             }
        }
        stage('sonarQube') {
             steps {
                 withCredentials([usernamePassword(credentialsId: 'sonarcredit', usernameVariable: 'sonarU', passwordVariable: 'sonarp')]) {
                 sh 'mvn sonar:sonar -Dsonar.login=$sonarU -Dsonar.password=$sonarp '
             }
          }
        }
        stage('Deploy SNAPSHOT') {
               steps {
                  withCredentials([usernamePassword(credentialsId: 'nexuscredit', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                  sh "mvn deploy -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://localhost:8081/repository/maven-snapshots/ -Dusername=$USERNAME -Dpassword=$PASSWORD"
               }
            }
        }
        stage('Docker Build') {
               steps {
                  script {
                      sh 'docker build -t emna050/stationski:1.0 .'
                      }
               }
        }
        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker_credit', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
                    sh 'docker push  emna050/stationski:1.0 '
                }
            }
        }
        stage('Run Docker Compose') {
             steps {
                dir('.') {
                    sh 'ls -la'
                    sh 'docker compose up -d'
                    sh 'docker-compose logs mysqldb'
               }
             }
        }
        post {
            success {
                script {
                    def email = credentials('email_address') // Use the ID you set earlier
                    mail(
                        to: email,
                        subject: "Successful Pipeline: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                        body: "The deployment was successful for ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}. You can review the details at ${env.BUILD_URL}."
                    )
                }
            }
            failure {
                script {
                    def email = credentials('email_address') // Use the same ID here
                    mail(
                        to: email,
                        subject: "Failed Pipeline: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                        body: "The deployment failed for ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}. Please review the details at ${env.BUILD_URL}."
                    )
                }
            }
        }

    }
}