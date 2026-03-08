pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = 'dockerhub-creds'
        DOCKERHUB_REPO = 'manas0911user'
    }

    stages {

        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build Backend Image') {
            steps {
                sh '''
                docker build -t $DOCKERHUB_REPO/backend:latest ./app-code/backend
                '''
            }
        }

        stage('Build Frontend Image') {
            steps {
                sh '''
                docker build -t $DOCKERHUB_REPO/frontend:latest ./app-code/frontend
                '''
            }
        }

        stage('Push Backend Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: "$DOCKERHUB_CREDENTIALS",
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {
                    sh '''
                    echo $PASS | docker login -u $USER --password-stdin
                    docker push $DOCKERHUB_REPO/backend:latest
                    '''
                }
            }
        }

        stage('Push Frontend Image') {
            steps {
                sh '''
                docker push $DOCKERHUB_REPO/frontend:latest
                '''
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh '''
                kubectl apply -f kubernetes-manifests/database/
                kubectl apply -f kubernetes-manifests/backend/
                kubectl apply -f kubernetes-manifests/frontend/
                '''
            }
        }

    }
}