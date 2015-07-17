/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;
/**
 * 
 * @author Ramakrishna Gundala
 *
 */
public class AdverseEventRecommendedReportQuery extends AbstractQuery {
	 
	 private static final String ADVERSE_EVENT = "adverseEventId";
	 private static final String REPORT_DEFINITION = "reportDefinitionId";
	 private static final String DUE_DATE = "dueDate";
	 private static final String AE_REPORTED = "aeReported";
	 
	 public AdverseEventRecommendedReportQuery() {
		 super("select distinct aerr from AdverseEventRecommendedReport aerr ");
		// join("aerr.adverseEvent as ae ");
		// join("aerr.reportDefinition as rd ");
	 }
	 
	 public void filterByAdverseEvent(Integer adverseEventId) {
		 join("aerr.adverseEvent as ae ");
		 andWhere("ae.id =:" + ADVERSE_EVENT);
		 setParameter(ADVERSE_EVENT , adverseEventId);
	 }
	 
	 public void filterByReportingDefinition(Integer reportDefinitionId){
		 andWhere("rd.id =:" + REPORT_DEFINITION);
		 setParameter(REPORT_DEFINITION, reportDefinitionId);
	 }
	 
	 public void filterByAEsNotAddedToReport(){
		 andWhere("aeReported =:" + AE_REPORTED);
		 setParameter(AE_REPORTED, false);
	 }
	 
}
