pipeline {
    agent any

    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Pulling from GitHub...'
                git branch: 'Ikram_Dhib',
                    url: 'https://github.com/ikramdhib/StationSki5SAE2Devops.git',
                    credentialsId: 'JenkinsPipeline'
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

        stage('SonarQube Analysis') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'sonarCredentials', usernameVariable: 'SONAR_USER', passwordVariable: 'SONAR_PASS')]) {
                    sh 'mvn sonar:sonar -Dsonar.login=$SONAR_USER -Dsonar.password=$SONAR_PASS'
                }
            }
        }

        stage('Deploy SNAPSHOT') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus-credentials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh "mvn deploy -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://localhost:8081/repository/maven-snapshots/ -Dusername=$USERNAME -Dpassword=$PASSWORD"
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    // Construire l'image Docker
                    sh 'docker build -t stationski .'
                }
            }
        }

        stage('Docker Run') {
            steps {
                script {
                    // Lancer le conteneur Docker
                    sh 'docker run -d -p 8089:8089 --name stationski_container stationski'
                }
            }
        }
    }

    post {
        always {
            // Nettoyer le conteneur après l'exécution pour éviter les conflits
            script {
                sh 'docker rm -f stationski_container || true'
            }
        }
    }
}
