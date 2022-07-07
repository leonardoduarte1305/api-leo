pipeline {
	 agent {
	    any
     }
	 tools {
	     maven "maven-3"
	     jdk "jdk-11"
	 }
	 environment {
	    MAJOR_VERSION = 1
	    MAVEN_OPTS = "-Djansi.force=true -Xmx512m"
	    SERVICE_NAME = "api-leo"
	    VERSION = "1.0"
	 }
	 stages {
	    stage('CI'){
	        stages {
	            stage("Setup"){
                    steps {
                        script {
                            echo "Environments:"
                        }
                        sh "printenv | sort"
                    }
                }
                stage("Init"){
                    steps{
                        sh "echo Stage Init"
                        sh "${MAVEN} version:set -DnewVersion=${VERSION}"
                    }
                }
                stage("Build"){
                    steps{
                        sh "echo Stage Build"
                        sh "${MAVEN} clean install -Dmaven.test.skip.exec -Pci"
                    }
                }
                stage("Unit Tests"){
                    steps{
                        sh "echo Stage Unit Tests"
                        sh "${MAVEN} test"
                    }
                }
                stage("Integration Tests"){
                    steps{
                        sh "echo Stage Integration Tests"
                        sh "${MAVEN} verify -DskipTests"
                    }
                }
                stage("Git Tag"){
                    steps{
                        sh "echo Stage Git Tag"
                        when {
                            branch "master"
                        }
                        steps {
                            sh "git tag ${VERSION}"
                            sh "git push origin ${VERSION}"
                        }
                    }
                }
                stage("Deploy Artifacts"){
                    steps{
                        sh "echo Stage Deploy Artifacts"
                        steps {
                            sh "${MAVEN} deploy -DskipTests -DskipITs -Pci"
                        }
                    }
                }
	        }
	    }
	    stage('CD'){
	        stages {
	            stage("Printing"){
	                when {
	                    branch "master"
	                }
	                steps {
	                    sh "echo CD Stage performing nothing."
	                }
	            }
	        }
	    }
	 }
}