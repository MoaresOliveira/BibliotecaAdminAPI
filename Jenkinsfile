pipeline {
    agent any

    environment {
        IMAGE_NAME = 'moaresoliveira/biblioteca-admin-java'  // Nome da imagem Docker
        CONTAINER_NAME = 'biblioteca-admin-java'  // Nome do container Docker
    }

    stages {
        stage('Checkout') {
            steps {
                // Clona o código do repositório
                git 'https://github.com/MoaresOliveira/BibliotecaAdminAPI'
            }
        }

        stage('Build') {
            steps {
                // Executa o Maven para construir o .jar
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build da imagem Docker
                    sh 'docker build -t ${IMAGE_NAME} .'
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    // Parar e remover o container se já estiver em execução
                    sh 'docker stop ${CONTAINER_NAME} || true && docker rm ${CONTAINER_NAME} || true'

                    // Executar o container Docker
                    sh 'docker run -d --name ${CONTAINER_NAME} ${IMAGE_NAME}'
                }
            }
        }
    }

    post {
        always {
            // Clean up o workspace após a execução da pipeline
            cleanWs()
        }
    }
}
