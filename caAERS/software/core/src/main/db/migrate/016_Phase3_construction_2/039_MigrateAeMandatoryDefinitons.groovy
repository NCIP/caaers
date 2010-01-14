class MigrateAeMandatoryDefinitions extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	    
		if (databaseMatches('postgresql')){
		
			execute("INSERT INTO ae_mandatory_field_defs(version, field_path, mandatory, study_id, grid_id) select 0, 'adverseEvents[]', 0, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(version, field_path, mandatory, study_id, grid_id) select 0, 'adverseEvents[].grade', 1, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(version, field_path, mandatory, study_id, grid_id) select 0, 'adverseEvents[].adverseEventCtcTerm', 0, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(version, field_path, mandatory, study_id, grid_id) select 0, 'adverseEvents[].adverseEventCtcTerm.term', 1, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(version, field_path, mandatory, study_id, grid_id) select 0, 'adverseEvents[].detailsForOther', 0, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(version, field_path, mandatory, study_id, grid_id) select 0, 'adverseEvents[].startDate', 0, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(version, field_path, mandatory, study_id, grid_id) select 0, 'adverseEvents[].endDate', 0, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(version, field_path, mandatory, study_id, grid_id) select 0, 'adverseEvents[].attributionSummary', 0, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(version, field_path, mandatory, study_id, grid_id) select 0, 'adverseEvents[].hospitalization', 0, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(version, field_path, mandatory, study_id, grid_id) select 0, 'adverseEvents[].expected', 0, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(version, field_path, mandatory, study_id, grid_id) select 0, 'adverseEvents[].eventLocation', -1, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(version, field_path, mandatory, study_id, grid_id) select 0, 'adverseEvents[].comments', -1, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(version, field_path, mandatory, study_id, grid_id) select 0, 'adverseEvents[].participantAtRisk', -1, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(version, field_path, mandatory, study_id, grid_id) select 0, 'adverseEvents[].eventApproximateTime.hourString', -1, s.id, null from studies s");
			
			execute("INSERT INTO ae_mandatory_field_defs(version, field_path, mandatory, study_id, grid_id) select 0, 'adverseEvents[].outcomes', -1, s.id, null from studies s where s.adeers_reporting = true");
			execute("INSERT INTO ae_mandatory_field_defs(version, field_path, mandatory, study_id, grid_id) select 0, 'adverseEvents[].outcomes', 0, s.id, null from studies s where s.adeers_reporting = false");
			
	    }else if (databaseMatches('oracle')){
	    	
	    	execute("INSERT INTO ae_mandatory_field_defs(id, version, field_path, mandatory, study_id, grid_id) select SEQ_AE_MANDATORY_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[]', 0, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(id, version, field_path, mandatory, study_id, grid_id) select SEQ_AE_MANDATORY_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].grade', 1, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(id, version, field_path, mandatory, study_id, grid_id) select SEQ_AE_MANDATORY_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].adverseEventCtcTerm', 0, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(id, version, field_path, mandatory, study_id, grid_id) select SEQ_AE_MANDATORY_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].adverseEventCtcTerm.term', 1, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(id, version, field_path, mandatory, study_id, grid_id) select SEQ_AE_MANDATORY_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].detailsForOther', 0, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(id, version, field_path, mandatory, study_id, grid_id) select SEQ_AE_MANDATORY_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].startDate', 0, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(id, version, field_path, mandatory, study_id, grid_id) select SEQ_AE_MANDATORY_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].endDate', 0, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(id, version, field_path, mandatory, study_id, grid_id) select SEQ_AE_MANDATORY_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].attributionSummary', 0, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(id, version, field_path, mandatory, study_id, grid_id) select SEQ_AE_MANDATORY_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].hospitalization', 0, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(id, version, field_path, mandatory, study_id, grid_id) select SEQ_AE_MANDATORY_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].expected', 0, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(id, version, field_path, mandatory, study_id, grid_id) select SEQ_AE_MANDATORY_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].eventLocation', -1, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(id, version, field_path, mandatory, study_id, grid_id) select SEQ_AE_MANDATORY_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].comments', -1, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(id, version, field_path, mandatory, study_id, grid_id) select SEQ_AE_MANDATORY_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].participantAtRisk', -1, s.id, null from studies s");
			execute("INSERT INTO ae_mandatory_field_defs(id, version, field_path, mandatory, study_id, grid_id) select SEQ_AE_MANDATORY_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].eventApproximateTime.hourString', -1, s.id, null from studies s");
			
			execute("INSERT INTO ae_mandatory_field_defs(id, version, field_path, mandatory, study_id, grid_id) select SEQ_AE_MANDATORY_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].outcomes', -1, s.id, null from studies s where s.adeers_reporting = 1");
			execute("INSERT INTO ae_mandatory_field_defs(id, version, field_path, mandatory, study_id, grid_id) select SEQ_AE_MANDATORY_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].outcomes', 0, s.id, null from studies s where s.adeers_reporting = 0");	
	    }
	}
	void down(){
		//not required
	}
}