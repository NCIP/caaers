class AlterSiteInvestigators extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		execute('ALTER TABLE site_investigators DROP CONSTRAINT fk_site_inv_invs')
	}
	void down(){
		execute('ALTER TABLE site_investigators ADD CONSTRAINT fk_site_inv_invs FOREIGN KEY (investigator_id) REFERENCES investigators (id)')
	}
}