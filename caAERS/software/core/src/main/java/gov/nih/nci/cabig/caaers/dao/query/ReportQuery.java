package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.ReportStatus;

public class ReportQuery extends AbstractQuery {
	
	public static String REPORT_ALIAS = "report";
	
	public static final String AE_REPORTING_PERIOD_ALIAS = "aeRp";
	
	public static final String EXPEDITED_AE_REPORT_ALIAS = "aeReport";
	
	public static final String REPORT_DEFINITION_ALIAS = "repDef";
	
	public static final String REPORT_VERSION_ALIAS = "repVer";
	
	public static final String TREATMENT_ASSIGNMENT_ALIAS = "trAss";
	
	public static final String TREATMENT_INFORMATION_ALIAS = "trInf";
	
	public static final String AE_RESPONSE_DESC_ALIAS = "aeResDesc";
	
	public ReportQuery() {
		super("select distinct "+REPORT_ALIAS+" from Report " + REPORT_ALIAS);
	}

}
