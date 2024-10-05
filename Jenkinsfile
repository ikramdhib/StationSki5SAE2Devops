pipeline{
	agent any 

	stages{
	 stage('Checkout GIT'){
		steps{
		 echo:'Pulling..'
		 git branch :'Ikram_Dhib'
		 url:'https://github.com/ikramdhib/StationSki5SAE2Devops.git'
		 credentialsId:'JenkinsPipeline'
}
}
   stage('Build'){
		steps{
		 sh:'mvn compile'
}
}
}
}
