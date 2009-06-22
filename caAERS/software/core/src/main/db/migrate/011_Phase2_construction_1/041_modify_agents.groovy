class UpdateAgents extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
	 	
      	execute("update agents set name = 'Alvocidib (flavopiridol)' where id = 443");
    }
      
    void down() {
       
    }
}


