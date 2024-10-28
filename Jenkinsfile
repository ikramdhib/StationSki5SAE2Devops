pipeline {
    agent any
    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Pulling...'
                git(
                    branch: 'master',
                    url: 'https://github.com/ikramdhib/StationSki5SAE2Devops',
                    credentialsId: 'f446726c-9547-408e-8d9b-87c55af4c051'
                )
            }
        }
        stage('Testing Maven') {
            steps {
                sh "mvn -version"
            }
        }
    }
}
