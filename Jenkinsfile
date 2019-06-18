pipeline {
        agent any
        tools {
            jdk 'java8'
           // maven 'Maven 3.3.9'
        }

    stages{
            stage('ECHO stage'){
                    steps{
                    echo 'stage ECHO'
                    }
            }
            
        stage('SCM veryfing'){
            steps{
                    echo 'stage SCM'
                    git 'https://github.com/albert011111/ContactsBook-App'
                   
            }
        }

        stage('MVN compile'){
                steps{
                        echo 'stage MVN'
                 }
        }
    }
}
