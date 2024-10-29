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
                    sh 'docker build -t ikramdhibikram/stationski:1.0 .'
                }
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    script {
                        sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
                        sh 'docker push ikramdhibikram/stationski:1.0'
                    }
                }
            }
        }
       stage('Run Docker Compose') {
           steps {
           dir('.') {
               sh 'ls -la'
               sh 'docker compose up -d'
               sh 'docker-compose logs mysqldb'
               }
           }
       }
        stage('Setup Grafana') {
                   steps {
                       withCredentials([usernamePassword(credentialsId: 'grafana-credentials', usernameVariable: 'GRAFANA_USER', passwordVariable: 'GRAFANA_PASS')]) {
                                              script {
                                                  // Wait for Grafana to start
                                                  sleep 30 // Adjust as needed

                                                  // Define Prometheus URL; adjust if Prometheus runs on host
                                                  def prometheusUrl = 'http://localhost:9090' // Adjust if necessary

                                                  sh """
                                                  curl -X POST -H "Authorization: Basic \$(echo -n \$GRAFANA_USER:\$GRAFANA_PASS | base64)" \
                                                  -H "Content-Type: application/json" \
                                                  -d '{
                                                      "name": "Prometheus",
                                                      "type": "prometheus",
                                                      "url": "${prometheusUrl}",
                                                      "access": "proxy",
                                                      "basicAuth": false
                                                  }' \
                                                  http://localhost:3000/api/datasources
                                                  """
                                              }
                       }
                   }
        }

    }
}
