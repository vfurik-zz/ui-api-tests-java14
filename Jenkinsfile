def notify(col, msg) {
    slackSend channel: "aqa_jenkins_builds",
            color: col,
            message: msg
}

pipeline {
    agent any
    stages {
        stage('Running tests') {
            steps {
                sh './gradlew clean api'
                echo 'generating report'
            }
            post {
                always {
                    allure([
                        includeProperties: true,
                        jdk              : '',
                        properties       : [],
                        reportBuildPolicy: 'ALWAYS',
                        results          : [[path: 'build/allure-results']]
                    ])
                }
            }
        }
    }
}