class UpdateSiteInvestigators extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		execute('UPDATE site_investigators SET INVESTIGATOR_ID=INVESTIGATOR_ID+30000')
	}
	void down(){
		execute('UPDATE site_investigators SET INVESTIGATOR_ID=INVESTIGATOR_ID-30000')
	}
}