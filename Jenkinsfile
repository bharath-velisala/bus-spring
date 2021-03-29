pipeline{
    triggers{
        pollSCM('* * * * *')
    }
    agent any
    tools{
        maven 'maven'
    }

    stages{
        stage('maven clean'){
            steps{
                sh 'mvn clean'
            }
        }
        stage('maven compile'){
            steps{
                sh 'mvn compile'
            }
        }
        stage('maven tests'){
            steps{
                    sh 'mvn test'
            }
        }

        stage('sonar analysis'){
            steps{
                withSonarQubeEnv('sonarQube'){
                    withMaven(maven:'maven'){
                        sh 'mvn sonar:sonar'
                    }
                }
            }

        }
        stage('maven package'){
            steps{
                sh 'mvn package'
            }
        }

    }

    post{
      success{
           rtUpload (
    serverId: 'Jfrog',
    spec: '''{
          "files": [
            {
              "pattern": "target/*.jar",
              "target": "art-doc-dev-loc-spring"
            }
         ]
    }''',
    buildName: 'holyFrog',
    buildNumber: '1'
)


        rtDownload (
        serverId: "Jfrog",
        spec:
              """{
                "files": [
                  {
                    "pattern": "art-doc-dev-loc-spring/**",
                    "target": "artifacts/"      
                  }
               ]
              }"""
      )
       sshagent(['ed7c8a08-d943-4a66-a42c-e5e719bdeb9d']){
                    sh 'scp -r /var/jenkins_home/workspace/collegaServay/artifacts/*.jar ubuntu@13.127.162.79:/home/ubuntu/artifacts'
        }
            mail bcc: '', body: 'build was successful ', cc: '', from: '', replyTo: '', subject: 'build successful', to: 'bharath.velisala@gmail.com'

      }
      failure {
            echo 'Build failed :('
         mail bcc: '', body: 'build failure ', cc: '', from: '', replyTo: '', subject: 'build failure', to: 'bharath.velisala@gmail.com'

        }

  }
}   
  
