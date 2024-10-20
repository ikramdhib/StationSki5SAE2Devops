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

   stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube Analysis...'
               withCredentials([string(credentialsId: 'SONAR_TOKEN_CREDENTIAL_ID', variable: 'SONAR_TOKEN')]) {
                   sh "mvn sonar:sonar -Dsonar.projectKey=station-ski-devops -Dsonar.host.url=http://localhost:9000 -Dsonar.token=${SONAR_TOKEN} -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
               }
            }
        }
    }
}
