# 🚀 Three-Tier DevSecOps Project

A complete end-to-end DevOps implementation of a **Three-Tier Application** deployed using **Docker, Kubernetes, Jenkins, and AWS EC2** with CI/CD automation.

This project demonstrates how modern DevOps workflows automate the entire process from:

Code Commit → Build → Containerization → Deployment → Production Infrastructure.

The architecture includes **Frontend, Backend, and Database layers** deployed using Kubernetes and automated through a Jenkins pipeline.

---

# 📌 Project Overview

This project implements a **Three-Tier Architecture** using containerization and Kubernetes orchestration.

The three layers include:

1. Frontend Layer
2. Backend Layer
3. Database Layer

Each layer runs inside Docker containers and is orchestrated through Kubernetes deployments and services.

The CI/CD pipeline ensures that whenever code changes occur, the application is automatically built and deployed.

---

# 🧱 Architecture

The system follows a modern cloud-native architecture.

```
Developer
   │
   │ git push
   ▼
GitHub Repository
   │
   ▼
Jenkins CI/CD Pipeline
   │
   │ Docker Build
   ▼
DockerHub Registry
   │
   ▼
Kubernetes Cluster (K3s)
   │
 ┌───────────────┬───────────────┬
 ▼               ▼               ▼
Frontend        Backend        PostgreSQL
Deployment      Deployment     Deployment
```

---

# 🖥️ Infrastructure

The infrastructure is built using **AWS EC2 instances**.

Two EC2 servers are used.

### Jenkins Server

Responsible for:

- Running CI/CD pipeline
- Building Docker images
- Pushing images to DockerHub
- Deploying to Kubernetes

### Kubernetes Server

Responsible for:

- Running Kubernetes cluster
- Deploying application workloads
- Managing containers

---

# 🧰 Technology Stack

This project uses the following technologies.

### Cloud

AWS EC2

### Containerization

Docker

### Container Registry

DockerHub

### Orchestration

Kubernetes (K3s)

### CI/CD

Jenkins

### Source Control

Git  
GitHub

### Programming

Python (Flask Backend)  
HTML / CSS (Frontend)

### Database

PostgreSQL

---

# 📂 Project Structure

```
three-tier-devsecops
│
├── app-code
│   ├── backend
│   │   ├── app.py
│   │   ├── Dockerfile
│   │   └── requirements.txt
│   │
│   ├── frontend
│   │   ├── Dockerfile
│   │   └── index.html
│   │
│   └── database
│
├── kubernetes-manifests
│   ├── backend
│   │   ├── deployment.yaml
│   │   └── service.yaml
│   │
│   ├── frontend
│   │   ├── deployment.yaml
│   │   └── service.yaml
│   │
│   └── database
│       └── deployment.yaml
│
├── Jenkinsfile
│
└── README.md
```

---

# ⚙️ Application Components

## Frontend

The frontend is a lightweight HTML interface served through a container.

Responsibilities:

- Provide user interface
- Send requests to backend API
- Display backend response

Runs on port:

```
80
```

---

## Backend

The backend is built using **Python Flask API**.

Responsibilities:

- Handle HTTP requests
- Process business logic
- Communicate with database

Runs on port:

```
5000
```

---

## Database

PostgreSQL is used as the database layer.

Responsibilities:

- Store application data
- Support backend queries

Runs as a container inside Kubernetes.

---

# 🐳 Docker Implementation

Each service is containerized using Docker.

Containers provide:

- Consistent environments
- Easy deployment
- Scalability
- Portability

Example Backend Dockerfile:

```
FROM python:3.9

WORKDIR /app

COPY requirements.txt .

RUN pip install -r requirements.txt

COPY . .

CMD ["python","app.py"]
```

---

# ☸️ Kubernetes Deployment

Kubernetes is used for container orchestration.

It manages:

- Pod scheduling
- Service networking
- Container lifecycle
- Scaling and reliability

---

# Kubernetes Deployments

Each application layer runs as its own deployment.

- frontend-deployment
- backend-deployment
- postgres-deployment

---

# Kubernetes Services

Services expose pods to the network.

Frontend uses **NodePort service**.

Example configuration:

```
Type: NodePort
Port: 80
NodePort: 30007
```

---

# 🔁 CI/CD Pipeline

Jenkins automates the entire application lifecycle.

Pipeline stages include:

1. Clone Repository
2. Build Docker Images
3. Docker Login
4. Push Images to DockerHub
5. Deploy to Kubernetes

---

# Jenkins Pipeline Flow

```
Stage 1
Clone GitHub Repository

Stage 2
Build Backend Docker Image

Stage 3
Build Frontend Docker Image

Stage 4
DockerHub Login

Stage 5
Push Docker Images

Stage 6
Deploy to Kubernetes
```

---

# Jenkinsfile Example

```
pipeline {
    agent any

    stages {

        stage('Clone Repo') {
            steps {
                git 'https://github.com/manas0911user/three-tier-devsecops.git'
            }
        }

        stage('Build Images') {
            steps {
                sh 'docker build -t backend ./app-code/backend'
                sh 'docker build -t frontend ./app-code/frontend'
            }
        }

        stage('Push Images') {
            steps {
                sh 'docker push backend'
                sh 'docker push frontend'
            }
        }

        stage('Deploy') {
            steps {
                sh 'kubectl apply -f kubernetes-manifests/'
            }
        }
    }
}
```

---

# 🌐 Accessing the Application

Once deployed, the application can be accessed using:

```
http://EC2_PUBLIC_IP:30007
```

This loads the frontend UI which communicates with the backend service.

---

# 🔐 Security Considerations

Security best practices used in this project:

- SSH key authentication
- Jenkins credential management
- DockerHub secure login
- Kubernetes isolated workloads

---

# 📈 Benefits of This Architecture

### Scalability

Kubernetes allows scaling services independently.

### Automation

CI/CD pipeline automates deployments.

### Reliability

Containers provide consistent runtime environments.

### Portability

Docker images can run on any platform.

---

# 🚀 Deployment Steps

Clone repository

```
git clone https://github.com/manas0911user/three-tier-devsecops.git
```

Build Docker images

```
docker build -t backend ./app-code/backend
docker build -t frontend ./app-code/frontend
```

Push images

```
docker push backend
docker push frontend
```

Deploy to Kubernetes

```
kubectl apply -f kubernetes-manifests/
```

Verify deployment

```
kubectl get pods
kubectl get svc
```

---

# 📊 Future Improvements

Potential improvements for this project include:

- Kubernetes Horizontal Pod Autoscaling
- GitHub Webhook automation
- Monitoring using Prometheus and Grafana
- Centralized logging using ELK Stack
- Infrastructure automation using Terraform
- Helm chart deployment

---

# 🎯 Learning Outcomes

Through this project the following DevOps skills were developed.

- Cloud infrastructure management
- Docker containerization
- Kubernetes orchestration
- CI/CD automation
- Microservice architecture
- DevOps best practices

---

# ⭐ If you like this project

Give it a ⭐ on GitHub.