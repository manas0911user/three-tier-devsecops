pipeline {
    agent any

    environment {
        DOCKERHUB = "manas0911user"
    }

    stages {

        stage('Clone Repo') {
            steps {
                git 'https://github.com/manas0911user/three-tier-devsecops.git'
            }
        }

        stage('Build Backend Image') {
            steps {
                sh "docker build -t $DOCKERHUB/backend:latest app-code/backend"
            }
        }

        stage('Build Frontend Image') {
            steps {
                sh "docker build -t $DOCKERHUB/frontend:latest app-code/frontend"
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    sh "echo $PASS | docker login -u $USER --password-stdin"
                }
            }
        }

        stage('Push Images') {
            steps {
                sh "docker push $DOCKERHUB/backend:latest"
                sh "docker push $DOCKERHUB/frontend:latest"
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh "kubectl apply -f kubernetes-manifests/"
            }
        }

    }
}