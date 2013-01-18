class AddNewAEFieldConfiguration extends edu.northwestern.bioinformatics.bering.Migration {
    void up(){

        if (databaseMatches('postgresql')){
            execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'adverseEvents[].gradedDate', 0, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
        }else if (databaseMatches('oracle')){
            execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'adverseEvents[].gradedDate', 0, 'gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab', null )");
        }
    }
    void down(){
        //not required
    }
}