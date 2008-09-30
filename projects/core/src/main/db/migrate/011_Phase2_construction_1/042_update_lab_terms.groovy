class UpdateLabTerms extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
        execute("update lab_terms set term='Glutamic-pyruvate transferase (ALT, SGPT)' where term = 'Glutamic-pyruvate transferase (ALT , SGPT)'");
        execute("update lab_terms set term='Glutamic-oxaloacetic transferase (AST, SGOT)' where term = 'Glutamic-oxaloacetic transferase (AST , SGOT)'");
	}
	
	void down(){
		
	}
}