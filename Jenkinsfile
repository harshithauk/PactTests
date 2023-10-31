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
                sh 'mvn -f PactDemoConsumer/pom.xml'
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('ConsumerTest') {
            steps {
                sh 'mvn -f PactDemoConsumer/pom.xml'
                sh 'mvn  test -Dtest=PactConsumerTest'
            }
        }
//         stage('ProviderTest') {
//             steps {
//                 sh 'mvn test -Dtest=PactProviderTest'
//             }
//         }
    }
}