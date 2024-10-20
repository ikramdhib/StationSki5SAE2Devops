pipeline {
    agent any

       environment {
            // Define the SonarQube server URL and the token from Jenkins credentials
            SONAR_HOST_URL = 'http://localhost:9000' // Local SonarQube URL
            SONAR_TOKEN = credentials('SONAR_TOKEN') // Fetch the token using the ID from Jenkins credentials
        }

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

          stage('Testing Maven') {
                    steps {
                        echo 'Test with Maven...'
                        sh 'mvn -version'
                    }
                }

   stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube Analysis...'
                withSonarQubeEnv('SonarQube') { // Ensure 'SonarQube' matches the name configured in Jenkins for your server
                    sh """
                        mvn sonar:sonar \
                        -Dsonar.projectKey=station-ski-devops \
                        -Dsonar.host.url=${SONAR_HOST_URL} \
                        -Dsonar.login=${SONAR_TOKEN}
                    """
                }
            }
        }
    }
}
