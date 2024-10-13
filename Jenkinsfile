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

          stage('Testing Maven') {
                    steps {
                        echo 'Test with Maven...'
                        sh 'mvn -version'
                    }
                }


                stage('Sonarqube') {
                            steps {
                                echo 'Building with Maven...'
                                sh 'mvn sonar:sonar, -Dsonar.login=eee -Dsonar.password=Sonar.123456'
                            }
                        }
    }
}
