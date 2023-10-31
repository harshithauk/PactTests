pipeline {
   agent any
   tools {
         maven 'maven 3.9.5'
         jdk 'java-11'
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
                sh 'mvn -B -DskipTests clean package'
            }
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