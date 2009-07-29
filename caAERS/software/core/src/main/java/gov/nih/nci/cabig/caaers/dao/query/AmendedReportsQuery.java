package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;

/**
 * This class will find out the {@link Report} that got amended recently. 
 *  
 * @author Biju Joseph
 *
 */
public class AmendedReportsQuery extends AbstractQuery {
	
	private static String queryString = "select r from ReportVersion rv" +
			" join rv.report as r" +
			" join r.reportDefinition as rd" +
			" order by rv.amendedOn desc";
	 
	public AmendedReportsQuery(){
		super(queryString);
	}
	
	public void filterByOrganization(Integer orgId){
		andWhere("rd.organization.id =:" + "orgId");
		setParameter("orgId", orgId);
	}
	
	public void filterByGroup(Integer groupId){
		andWhere("rd.group.id =:" + "groupId");
		setParameter("groupId", groupId);
	}
	
	public void filterByExpeditedAdverseEventReport(Integer aeReportId){
		andWhere("r.aeReport.id =:" + "aeReportId");
		setParameter("aeReportId", aeReportId);
	}
	
	public void filterByReportStatus(ReportStatus status){
		andWhere("rv.reportStatus =:" + "status");
		setParameter("status", status);
	}
}
