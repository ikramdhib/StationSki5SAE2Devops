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

         stage('sonarCube') {
            steps {
                dir('gestion-station-ski') { // Change le répertoire ici
                    sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Sonar50711246@'// Utilise mvnw pour exécuter Maven
                    
                }
            }
    }
        
}
}

