pipeline {
    agent any

    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Pulling...'
                git branch: 'Mariem', url: 'https://github.com/ikramdhib/StationSki5SAE2Devops.git',
                credentialsId: 'GitHub-PAT-Jenkins'             }
        }
        stage('Build') {
            steps {
                echo 'Building with Maven...'
                sh 'mvn compile'
            }
        }
    }
}
