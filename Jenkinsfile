pipeline{
	agent any

	stages{
	 stage('Checkout GIT'){
		steps{
		 echo:'Pulling..'
		 git branch :'Emna_labidi'
		 url:'https://github.com/ikramdhib/StationSki5SAE2Devops.git'
		 credentialsId:'github-pat'
}
}
   stage('Build'){
		steps{
		 sh:'mvn compile'
}
}
}
}