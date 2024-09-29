pipeline {
    agent any
    
    stages {
        stage( 'Checkout GIT') {
            steps {
                echo 'Pulling...'
                git branch:'nihel_roui',
                url:'https://github.com/ikramdhib/StationSki5SAE2Devops'
                credentialsId: 'StationSki2025'
            }
        }
        stage('Build'){
            steps{
                sh:'mvn compile'
                
            }
            
        }
    }
}
