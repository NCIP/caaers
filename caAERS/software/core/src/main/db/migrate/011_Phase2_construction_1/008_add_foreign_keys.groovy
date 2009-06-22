class AddForeignKeys extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
    	execute('ALTER TABLE additional_information ADD CONSTRAINT FK_ai_ae_reports FOREIGN KEY (report_id) REFERENCES ae_reports (id)');
     	execute('ALTER TABLE adverse_events ADD CONSTRAINT FK_ae_ae_reports	FOREIGN KEY (report_id) REFERENCES ae_reports (id)');
     	execute('ALTER TABLE adverse_events ADD CONSTRAINT FK_ae_ae_routine_reports	FOREIGN KEY (routine_report_id) REFERENCES ae_routine_reports (id)');
     	execute('ALTER TABLE ae_medical_devices ADD CONSTRAINT FK_ae_md_ae_reports FOREIGN KEY (report_id) REFERENCES ae_reports (id)');
     	execute('ALTER TABLE ae_radiation_interventions ADD CONSTRAINT FK_ae_ri_ae_reports FOREIGN KEY (report_id) REFERENCES ae_reports (id)');
     	execute('ALTER TABLE ae_report_descriptions ADD CONSTRAINT FK_ae_report_desc_ae_reports FOREIGN KEY (report_id) REFERENCES ae_reports (id)');
     	execute('ALTER TABLE ae_reports ADD CONSTRAINT FK_ae_reports_pa FOREIGN KEY (assignment_id) REFERENCES participant_assignments (id)');
     	execute('ALTER TABLE ae_routine_reports ADD CONSTRAINT FK_ae_rreports_ta FOREIGN KEY (treatment_assignment_id) REFERENCES treatment_assignment (id)');
     	execute('ALTER TABLE ae_surgery_interventions ADD CONSTRAINT FK_ae_si_ae_reports FOREIGN KEY (report_id) REFERENCES ae_reports (id)');
     	execute('ALTER TABLE ae_terms ADD CONSTRAINT FK_ae_terms_ae FOREIGN KEY (adverse_event_id) REFERENCES adverse_events (id)');
     	execute('ALTER TABLE course_agents ADD CONSTRAINT FK_course_agents_treatments FOREIGN KEY (treatment_id) REFERENCES treatments (id)');
     	execute('ALTER TABLE disease_categories ADD CONSTRAINT FK_dcategories_dcategories FOREIGN KEY (parent_id) REFERENCES disease_categories (id)');
     	execute('ALTER TABLE disease_histories ADD CONSTRAINT FK_dhistories_sd FOREIGN KEY (study_disease_id) REFERENCES study_diseases (id)');
      	execute('ALTER TABLE disease_terminologies ADD CONSTRAINT FK_dterminology_study FOREIGN KEY (study_id) REFERENCES studies (id)');
      	execute('ALTER TABLE identifiers ADD CONSTRAINT FK_identifiers_organizations FOREIGN KEY (organization_id) REFERENCES organizations (id)');
      	execute('ALTER TABLE identifiers ADD CONSTRAINT FK_identifiers_participants FOREIGN KEY (participant_id) REFERENCES participants (id)');
      	execute('ALTER TABLE identifiers ADD CONSTRAINT FK_identifiers_studies FOREIGN KEY (stu_id) REFERENCES studies (id)');
    	
    }
    
    void down(){
    	execute('ALTER TABLE additional_information DROP CONSTRAINT FK_ai_ae_reports');
     	execute('ALTER TABLE adverse_events DROP CONSTRAINT FK_ae_ae_reports');
     	execute('ALTER TABLE adverse_events DROP CONSTRAINT FK_ae_ae_routine_reports');
     	execute('ALTER TABLE ae_attributions DROP CONSTRAINT FK_ae_attribs_ae');
     	execute('ALTER TABLE ae_medical_devices DROP CONSTRAINT FK_ae_md_ae_reports');
     	execute('ALTER TABLE ae_radiation_interventions DROP CONSTRAINT FK_ae_ri_ae_reports');
     	execute('ALTER TABLE ae_report_descriptions DROP CONSTRAINT FK_ae_report_desc_ae_reports');
     	execute('ALTER TABLE ae_reports DROP CONSTRAINT FK_ae_reports_pa');
     	execute('ALTER TABLE ae_routine_reports DROP CONSTRAINT FK_ae_rreports_ta');
     	execute('ALTER TABLE ae_surgery_interventions DROP CONSTRAINT FK_ae_si_ae_reports');
     	execute('ALTER TABLE ae_terms DROP CONSTRAINT FK_ae_terms_ae');
     	execute('ALTER TABLE course_agents DROP CONSTRAINT FK_course_agents_treatments');
     	execute('ALTER TABLE disease_categories DROP CONSTRAINT FK_dcategories_dcategories');
     	execute('ALTER TABLE disease_histories DROP CONSTRAINT FK_dhistories_sd');
     	execute('ALTER TABLE disease_terminologies DROP CONSTRAINT FK_dterminology_study');
    	execute('ALTER TABLE identifiers DROP CONSTRAINT FK_identifiers_organizations');
    	execute('ALTER TABLE identifiers DROP CONSTRAINT FK_identifiers_participants');
    	execute('ALTER TABLE identifiers DROP CONSTRAINT FK_identifiers_studies');
    }
}