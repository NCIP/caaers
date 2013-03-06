/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.synchronizer;

import java.util.List;

import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * @author chandrasekaravr
 */
public class ReportDeliveryDefinitionSynchronizer implements Migrator<gov.nih.nci.cabig.caaers.domain.report.ReportDefinition>{
	
	public void migrate(ReportDefinition xmlReportDefinition, ReportDefinition dbReportDefinition, DomainObjectImportOutcome<ReportDefinition> outcome) {
		List<ReportDeliveryDefinition> dbRepDeliveryDefs = dbReportDefinition.getDeliveryDefinitionsInternal();
		List<ReportDeliveryDefinition> xmlRepDeliveryDefs = xmlReportDefinition.getDeliveryDefinitions();
		if( dbRepDeliveryDefs != null) {
			dbRepDeliveryDefs.clear();
			if(xmlRepDeliveryDefs == null) {
				return;
			}//end of if
			for(ReportDeliveryDefinition defn: xmlRepDeliveryDefs){
				dbReportDefinition.addReportDeliveryDefinition(defn);
			} //end of for
		}//end of outer if
	}
}
