pipeline {
    agent any

    environment {
        IMAGE_NAME      = 'sns-api'
        CONTAINER_NAME  = 'sns-api-container'
        APP_PORT        = '8080'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            steps {
                // Java 21のMavenコンテナ内でテストを実行（JDK不要）
                sh '''
                    docker run --rm \
                      -v "$(pwd)":/app \
                      -w /app \
                      maven:3.9-eclipse-temurin-21 \
                      mvn test -B
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh """
                    docker build \
                      -t \${IMAGE_NAME}:\${BUILD_NUMBER} \
                      -t \${IMAGE_NAME}:latest \
                      .
                """
            }
        }

        stage('Deploy') {
            steps {
                sh """
                    docker stop \${CONTAINER_NAME} || true
                    docker rm   \${CONTAINER_NAME} || true
                    docker run -d \
                      --name \${CONTAINER_NAME} \
                      -p \${APP_PORT}:\${APP_PORT} \
                      --restart unless-stopped \
                      \${IMAGE_NAME}:latest
                """
            }
        }
    }

    post {
        success {
            echo "🎉 ビルド #\${BUILD_NUMBER} デプロイ完了！"
        }
        failure {
            echo "❌ 失敗。Console Output を確認してください。"
        }
        always {
            // 古いDockerイメージを削除
            sh 'docker image prune -f'
        }
    }
}