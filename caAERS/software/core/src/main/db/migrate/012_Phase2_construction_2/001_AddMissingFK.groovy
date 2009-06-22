class UpdateCsmPrivileges extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
		execute('ALTER TABLE metastatic_disease_sites ADD CONSTRAINT FK_mdisease_sites_dhistories FOREIGN KEY (disease_history_id) REFERENCES disease_histories (id)');
		execute('ALTER TABLE report_schedules ADD CONSTRAINT FK_rp_schedules_ae_reports FOREIGN KEY (report_id) REFERENCES ae_reports (id)');
		execute('ALTER TABLE site_investigators ADD CONSTRAINT FK_site_inv_invs FOREIGN KEY (investigator_id) REFERENCES investigators (id)');
		execute('ALTER TABLE site_investigators ADD CONSTRAINT FK_site_inv_orgs FOREIGN KEY (site_id) REFERENCES organizations (id)');
		execute('ALTER TABLE study_agents ADD CONSTRAINT FK_study_agents_agents FOREIGN KEY (agent_id) REFERENCES agents (id)');
		execute('ALTER TABLE study_agents ADD CONSTRAINT FK_sagents_studies FOREIGN KEY (study_id) REFERENCES studies (id)');
		execute('ALTER TABLE study_diseases ADD CONSTRAINT FK_sdiseases_studies FOREIGN KEY (study_id) REFERENCES studies (id)');
		execute('ALTER TABLE study_investigators ADD CONSTRAINT FK_study_invs_site_invs FOREIGN KEY (site_investigators_id) REFERENCES site_investigators (id)');
		execute('ALTER TABLE study_investigators ADD CONSTRAINT FK_sinv_study_orgs FOREIGN KEY (study_sites_id) REFERENCES study_organizations (id)');
		execute('ALTER TABLE study_organizations ADD CONSTRAINT FK_study_orgs_orgs FOREIGN KEY (site_id) REFERENCES organizations (id)');
		execute('ALTER TABLE study_organizations ADD CONSTRAINT FK_study_orgs_studies FOREIGN KEY (study_id) REFERENCES studies (id)');
		execute('ALTER TABLE study_personnel ADD CONSTRAINT FK_spersonnel_research_staffs FOREIGN KEY (research_staffs_id) REFERENCES research_staffs (id)');
		execute('ALTER TABLE study_personnel ADD CONSTRAINT FK_spersonnel_study_orgs FOREIGN KEY (study_sites_id) REFERENCES study_organizations (id)');
		execute('ALTER TABLE treatments ADD CONSTRAINT FK_treatments_ae_reports FOREIGN KEY (report_id) REFERENCES ae_reports (id);');
	}
	void down(){
		//no droping is allowed
	}
}