pipeline {
   agent any
    stages {
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