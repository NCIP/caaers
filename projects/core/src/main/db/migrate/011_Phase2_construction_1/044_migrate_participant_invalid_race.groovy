class MigrateParticpantRace extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
        execute("update participants set race='Unknown' where race in ('Hispanic or Latino','More than one race','Other')");
	}
	
	void down(){
		
	}
}