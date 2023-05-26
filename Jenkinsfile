 pipeline {
        agent any
        stages {
            stage('Test') {
                steps {
                    echo 'Hello World ... !!!!!'
                }
            }
            stage('SonarQube Analysis') {
                def mvn = tool 'Default Maven';
                withSonarQubeEnv {
                  sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=TibiAlex_Degree-Project-Java_AYhZniwhAUatK-iQLt-j"
                }
            }
        }
    }