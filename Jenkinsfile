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
        stage('sonarQube') {
             steps {
                 withCredentials([usernamePassword(credentialsId: 'sonarcredit', usernameVariable: 'sonarU', passwordVariable: 'sonarp')]) {
                 sh 'mvn sonar:sonar -Dsonar.login=$sonarU -Dsonar.password=$sonarp'
             }
          }
        }
    }
}
