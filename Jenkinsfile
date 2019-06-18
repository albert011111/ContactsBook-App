node {
    agent any
        tools {
            jdk 'Java 1.8u91'
            maven 'Maven 3.3.9'
        }

    stages{
        stage('SCM veryfing'){
            git 'https://github.com/albert011111/ContactsBook-App'
            echo 'stage SCM'
        }

        stage('MVN compile'){
            echo 'stage MVN'
        }
    }
}
