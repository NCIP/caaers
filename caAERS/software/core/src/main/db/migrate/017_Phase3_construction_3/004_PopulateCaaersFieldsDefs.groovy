class PopulateCaaersFieldsDefs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {

      if (databaseMatches('postgresql')) {
        execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'assignment.startDateOfFirstCourse', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'reportingPeriod.startDate', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'reportingPeriod.endDate', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'eportingPeriod.epoch', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'reportingPeriod.cycleNumber', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'reportingPeriod.treatmentAssignment', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
      } else if (databaseMatches('oracle')) {
        execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'assignment.startDateOfFirstCourse', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'eportingPeriod.startDate', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'eportingPeriod.endDate', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'eportingPeriod.epoch', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'reportingPeriod.cycleNumber', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'reportingPeriod.treatmentAssignmentt', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
      }
    }

    void down() {
      //not required
    }
}