pipeline {
        agent any
        tools {
            jdk 'java8'
           // maven 'Maven 3.3.9'
        }

    stages{
        stage('SCM veryfing'){
            steps{
                    git 'https://github.com/albert011111/ContactsBook-App'
                    echo 'stage SCM'
            }
        }

        stage('MVN compile'){
                steps{
                        echo 'stage MVN'
                 }
        }
    }
}
