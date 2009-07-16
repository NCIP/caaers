package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.Comparator;

public class ReportDefinitionComparator implements Comparator<ReportDefinition> {

    /**
     * The logic for comparing these reports definitons is reverse in the sense that, lesser is the
     * period of reporting, higher is the priority In other words, we are comparing the priorties
     */

    public int compare(ReportDefinition o1, ReportDefinition o2) {
    	long l1 = o1.getTimeScaleUnitType().getMilliSecondConversionFactor() * o1.getDuration();
    	long l2 = o2.getTimeScaleUnitType().getMilliSecondConversionFactor() * o2.getDuration();
    	return (int) (l2 - l1);
    	
    }

}
