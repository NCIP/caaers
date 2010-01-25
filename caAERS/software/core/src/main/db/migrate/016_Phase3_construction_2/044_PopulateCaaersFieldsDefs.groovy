class PopulateCaaersFieldsDefs extends edu.northwestern.bioinformatics.bering.Migration {
	void up(){
	    
		if (databaseMatches('postgresql')){
		
			execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'adverseEvents[]', 0, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'adverseEvents[].grade', 1, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'adverseEvents[].adverseEventCtcTerm', 0, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'adverseEvents[].adverseEventCtcTerm.term', 1, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'adverseEvents[].detailsForOther', 0, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'adverseEvents[].startDate', 0, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'adverseEvents[].endDate', 0, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'adverseEvents[].attributionSummary', 0, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'adverseEvents[].hospitalization', 0, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'adverseEvents[].expected', 0, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'adverseEvents[].eventLocation', -1, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'adverseEvents[].comments', -1, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'adverseEvents[].participantAtRisk', -1, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'adverseEvents[].eventApproximateTime.hourString', -1, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'adverseEvents[].outcomes', -1, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			
			execute("Update caaers_field_defs set mandatory = 0 where field_path = 'adverseEvents[].outcomes' and exists (select s.id from studies s where s.adeers_reporting = true)");
			
	    }else if (databaseMatches('oracle')){
	    	
	    	execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[]', 0, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].grade', 1, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].adverseEventCtcTerm', 0, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].adverseEventCtcTerm.term', 1, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].detailsForOther', 0, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].startDate', 0, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].endDate', 0, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].attributionSummary', 0, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].hospitalization', 0, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].expected', 0, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].eventLocation', -1, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].comments', -1, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].participantAtRisk', -1, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].eventApproximateTime.hourString', -1, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].outcomes', -1, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
			
			execute("Update caaers_field_defs set mandatory = 0 where field_path = 'adverseEvents[].outcomes' and exists (select s.id from studies s where s.adeers_reporting = 1)");
	    }
	}
	void down(){
		//not required
	}
}