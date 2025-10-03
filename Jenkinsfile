pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                // Run your tests
                bat 'mvn test'
            }
        }
    }

    post {
        always {
            // Publish test results
            junit '**/target/surefire-reports/*.xml'

            // Send email
            emailext (
                subject: "Jenkins Build: ${currentBuild.fullDisplayName}",
                body: """Build Status: ${currentBuild.currentResult}
                        Project: ${env.JOB_NAME}
                        Build Number: ${env.BUILD_NUMBER}
                        Check console output at: ${env.BUILD_URL}""",
                to: "recipient@example.com",
                attachLog: true
            )
        }
    }
}

