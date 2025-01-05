pipeline{
    agent any
    tools {
        maven 'maven_3_6_3'
    }
    stages{
        stage("CHEKOUT PROJECT"){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sametpehlivan/jenkins-docker-integration.git']])
            }
        }
        stage("BUILD PROJECT"){
            steps{
                bat 'mvn clean install'
            }
        }
        stage("GET APPLICATION VERSION"){
            steps{
                script {
                    def versionNumber = bat(script: "mvnw -q -Dexec.executable='echo' -Dexec.args='${project.version}' --non-recursive exec:exec", returnStdout: true).trim()
                    // OR
                        //def version = bat(script: "mvnw org.apache.maven.plugins:maven-help-plugin:3.1.0:evaluate -Dexpression=\"project.version\"", returnStdout: true).trim()
                        //def versionNumber = version.split("\n").find { it =~ /^[0-9]+\.[0-9]+\.[0-9]+$/ }
                    if (versionNumber) {
                        env.APP_VERSION = versionNumber.trim()
                    } else {
                        error "VERSION not found in the Maven output!"
                    }
                }
            }
        }
        stage("BUILD DOCKER AND PUSH IMAGE"){
            steps{
                script{
                    withCredentials([string(credentialsId: 'JENKINS_DOCKER', variable: 'docker_password'), string(credentialsId: 'JENKINS_DOCKER_USERNAME', variable: 'docker_username')]) {
                        env.IMAGE_NAME = "jenkins-docker-integration"
                        def tag_current = "${docker_username}/${env.IMAGE_NAME}:${env.APP_VERSION}"
                        def tag_latest = "${docker_username}/${env.IMAGE_NAME}:latest"
                        bat "docker build --build-arg app_version=${env.APP_VERSION} -t ${tag_current} -t ${tag_latest} ."
                        bat "docker login -u ${docker_username}  -p ${docker_password}"
                        bat "docker push ${tag_current}"
                        bat "docker push ${tag_latest}"
                    }
                }
            }
        }
    }
}
