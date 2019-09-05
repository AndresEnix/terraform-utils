node('linux') {
    ansiColor('xterm') {
        stage('checkout') {
            checkout scm
        }
        stage('build') {
            sh './gradlew clean build'
        }
    }
}
