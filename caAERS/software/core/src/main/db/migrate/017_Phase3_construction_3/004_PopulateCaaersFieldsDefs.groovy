class PopulateCaaersFieldsDefs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {

      if (databaseMatches('postgresql')) {
        execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'treatmentInformation.firstCourseDate', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'treatmentInformation.thisCourseStartDate', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'treatmentInformation.thisCourseEndDate', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'treatmentInformation.treatmentType', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'treatmentInformation.course', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(version, field_path, mandatory, tab_name, grid_id) values (0, 'treatmentInformation.treatmentAssignment', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
      } else if (databaseMatches('oracle')) {
        execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'treatmentInformation.firstCourseDate', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'treatmentInformation.thisCourseStartDate', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'treatmentInformation.thisCourseEndDate', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'treatmentInformation.treatmentType', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'treatmentInformation.course', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
        execute("INSERT INTO caaers_field_defs(id, version, field_path, mandatory, tab_name, grid_id) values (SEQ_CAAERS_FIELD_DEFS_ID.nextval, 0, 'treatmentInformation.treatmentAssignment', 0, 'gov.nih.nci.cabig.caaers.web.ae.CourseCycleTab', null )");
      }
    }

    void down() {
      //not required
    }
}