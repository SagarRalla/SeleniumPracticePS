pipeline {
    agent any   // Runs on any available Jenkins agent

    tools {
        maven 'Maven3'   
        jdk 'Java17'     
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Checking out code from GitHub..."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Building project with Maven..."
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running TestNG/Selenium tests..."
                bat 'mvn test'
            }
        }
    }

    post {
        always {
            echo "Publishing test reports..."
            junit '**/target/surefire-reports/*.xml'  // Publishes TestNG/JUnit reports
        }
    }
}
