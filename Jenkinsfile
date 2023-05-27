node {
  stage('SCM') {
    checkout scm
  }
  stage('Build') {
        steps {
          script {
            // Update the version of the maven-compiler-plugin
            def updatedVersion = '3.9.1'
            def pomXml = readFile('pom.xml')
            pomXml = pomXml.replaceFirst(/<artifactId>maven-compiler-plugin<\/artifactId>\s+<version>\d+\.\d+\.\d+<\/version>/, "<artifactId>maven-compiler-plugin</artifactId><version>${updatedVersion}</version>")
            writeFile(file: 'pom.xml', text: pomXml)
          }
        }
      }
  stage('SonarQube Analysis') {
    def mvn = tool 'maven';
    withSonarQubeEnv() {
      sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=TibiAlex_Degree-Project-Java_AYhZniwhAUatK-iQLt-j"
    }
  }
}