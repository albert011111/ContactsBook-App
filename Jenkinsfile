pipeline {
        agent any
        tools {
            jdk 'java8'
            maven 'maven_3_6_1'
        }

    stages{
            stage('ECHO stage'){
                    steps{                  
                    echo 'stage ECHO'
                    sh 'mvn clean compile'
                    }
            }
            
                    stage('MVN compile'){
                steps{
                        echo 'stage MVN'
                 }
        }
            
        stage('SCM veryfing'){
            steps{
                    echo 'stage SCM'
                    git 'https://github.com/albert011111/ContactsBook-App'
                   
            }
        }

    }
}
