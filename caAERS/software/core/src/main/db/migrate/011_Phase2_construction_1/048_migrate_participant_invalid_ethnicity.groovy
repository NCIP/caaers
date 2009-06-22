class MigrateParticpantEthnicity extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
        execute("update participants set ethnicity='Not Hispanic or Latino' WHERE ethnicity='Non Hispanic or Latino'");
		execute("update participants set gender = 'Unknown' WHERE gender = 'Not Reported'");
	}
	
	void down(){
		
	}
}