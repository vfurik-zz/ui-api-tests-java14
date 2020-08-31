def notify(col, msg) {
    slackSend channel: "aqa_jenkins_builds",
            color: col,
            message: msg
}

pipeline {
    agent any

    stages {
        stage("Initialization"){
            teps{
              script{ buildName "${currentBuild.number}_${config}"}
            }
        }
        stage('Notify slack build is started') {
            steps {
 /*               notify('#D4DADF', "Tests: ${config} is started")uncomment for slack notification*/
                  echo 'Sending message to slack'
            }
        }

        stage('Running tests') {
            steps {
                sh './gradlew clean config -Denv=${config}'
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
    post {cleanWs()}
    success {
       //notify('good', "Test: ${config} success (<${env.BUILD_URL}allure/|Open>)")
       echo 'Tests finished successfully'
    }
    failure {
       //notify('#FF3D3E', "Test: ${config}  failed (<${env.BUILD_URL}allure/|Open>)")
       ecgo 'Tests are failed'
    }
}