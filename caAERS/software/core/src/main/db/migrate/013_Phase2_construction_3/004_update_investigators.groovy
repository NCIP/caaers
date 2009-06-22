class UpdateInvestigators extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		execute('UPDATE investigators SET id = id+30000')
	}
	void down(){
		execute('UPDATE investigators SET id = id-30000')
	}
}