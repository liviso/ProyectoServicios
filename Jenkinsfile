pipeline {
    agent any
    environment {
        LOCAL_SERVER = '192.168.0.10'
    }
    tools {
        maven 'M_3_8_2'
    }
    stages {
        stage('Build and Analize') {
            steps {
                dir('microservicio-service/'){
                    echo 'Execute Maven and Analizing with SonarServer'
                    withSonarQubeEnv('SonarServer') {
                        sh "mvn clean package dependency-check:check sonar:sonar \
                            -Dsonar.projectKey=21_MyCompany_Microservice \
                            -Dsonar.projectName=21_MyCompany_Microservice \
                            -Dsonar.sources=src/main \
                            -Dsonar.coverage.exclusions=**/*TO.java,**/*DO.java,**/curso/web/**/*,**/curso/persistence/**/*,**/curso/commons/**/*,**/curso/model/**/* \
                            -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
                    }
                }
            }
        }
        stage('Quality Gate'){
            steps {
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: false
                }
            }
        }
        stage('Container Build') {
            steps {
                dir('microservicio-service/'){
                    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'dockerhub_id  ', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                        sh 'docker login -u $USERNAME -p $PASSWORD'
                        sh 'docker build -t microservicio-service .'
                    }
                }
            }
        }
        stage('Container Push Nexus') {
            steps {
                    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'dockernexus_id  ', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                        sh 'docker login ${LOCAL_SERVER}:8083 -u $USERNAME -p $PASSWORD'
                        sh 'docker tag microservicio-service:latest ${LOCAL_SERVER}:8083/repository/docker-private/microservicio_nexus:dev'
                    }
            }
        }
        stage('Container Run') {
            steps {
                sh 'docker stop microservicio-one || true'
                sh 'docker run -d --rm --name microservicio-one -p 8090:8090 ${LOCAL_SERVER}:8083/repository/docker-private/microservicio_nexus:dev'
            }
        }
    }
}