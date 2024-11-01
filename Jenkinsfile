pipeline {
    agent any
    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Pulling...'
                git(
                    branch: 'medazizzalila',
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
        stage('Maven Clean') {
            steps {
                sh "mvn clean"
            }
        }
        stage('Maven Compile') {
            steps {
                sh "mvn compile"
            }
        }
        stage('Maven Test with Coverage') {
    steps {
        sh 'mvn test'
    }
}

        stage('SonarQube Analysis') {
            steps {
                withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    sh 'mvn sonar:sonar -Dsonar.login=$SONAR_TOKEN'
                }
            }
        }
        stage('Maven Deploy to Nexus') {
            steps {
                echo 'Deploying to Nexus...'
                withCredentials([usernamePassword(credentialsId: 'nexus-credentials', usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASS')]) {
                    sh 'mvn deploy -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://localhost:8081/repository/maven-releases/ -Dusername=$NEXUS_USER -Dpassword=$NEXUS_PASS'
                }
            }
        }
    }
}
