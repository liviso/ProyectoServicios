pipeline {
    agent any
    tools {
        maven 'M_3_8_2'
    }
    stages {
        stage('Build and Analize') {
            steps {
                dir('microservicio-service/'){
                    echo 'Execute Maven and Analizing with SonarServer'
                    withSonarQubeEnv('SonarServer') {
                        sh "mvn clean package sonar:sonar \
                            -Dsonar.projectKey=21_MyCompany_Microservice \
                            -Dsonar.projectName=21_MyCompany_Microservice \
                            -Dsonar.sources=src/main \
                            -Dsonar.coverage.exclusions=**/*TO.java,**/*DO.java,**/curso/web/**/*,**/curso/persistence/**/*,**/curso/commons/**/*,**/curso/model/**/* \
                            -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
                    }
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
        stage('Container Run') {
            steps {
                sh 'docker run -d --rm --name microservicio-one -p 8090:8090 microservicio-service'
            }
        }
    }
}