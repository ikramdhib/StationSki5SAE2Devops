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
    }
}
