pipeline {
   agent any
   tools {
         maven 'M2_HOME'
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

        stage('Consumerbuild') {
            steps {
                sh 'mvn -f PactDemoConsumer/pom.xml clean package'
            }
        }
        stage('Providerbuild') {
            steps {
                sh 'mvn -f PactDemoProvider/pom.xml clean package'
            }
        }
        stage('ConsumerTest') {
            steps {
                sh 'mvn -f PactDemoConsumer/pom.xml  test -Dtest=PactConsumerTest'
            }
        }
        stage('ProviderTest') {
            steps {
                sh 'mvn -f PactDemoProvider/pom.xml test -Dtest=PactProviderTest'
            }
        }
    }
}