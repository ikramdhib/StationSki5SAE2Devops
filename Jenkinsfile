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

        stage('Testing Maven') {
            steps {
                echo 'Testing with Maven...'
                sh 'mvn -version'
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
                         withCredentials([usernamePassword(credentialsId: 'Nexus_Credential', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                              sh "mvn deploy -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://localhost:8081/repository/maven-snapshots/ -Dusername=$USERNAME -Dpassword=$PASSWORD"
                         }
                      }
               }


    }

}
