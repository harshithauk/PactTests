pipeline {
   agent any
   tools {
         maven 'MAVEN_HOME'
         jdk 'JAVA_HOME'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage('build') {
        steps {
            sh 'mvn -Dmaven.test.failure.ignore=true install'
        }
        post {
            success {
                junit 'target/surefire-reports/**/*.xml'
            }
        }
//             steps {
//                 sh 'mvn -B -DskipTests clean package'
//             }
        }
        stage('ConsumerTest') {
            steps {
                sh 'mvn test -Dtest=PactConsumerTest'
            }
        }
        stage('ProviderTest') {
            steps {
                sh 'mvn test -Dtest=PactProviderTest'
            }
        }
    }
}