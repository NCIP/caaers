package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.Comparator;

public class ReportDefinitionComparator implements Comparator<ReportDefinition> {

    /**
     * The logic for comparing these reports definitons is reverse in the sense that, lesser is the
     * period of reporting, higher is the priority In other words, we are comparing the priorties
     */

    public int compare(ReportDefinition o1, ReportDefinition o2) {

        int timeScaleResult = compareTimeScale(o1, o2);

        if (timeScaleResult == 0) {
            return comparePriority(o1, o2);
        } else {
            return timeScaleResult;
        }
    }

    private int compareTimeScale(ReportDefinition o1, ReportDefinition o2) {

        if (o1.getTimeScaleUnitType().getCode().intValue() > o2.getTimeScaleUnitType().getCode()
                        .intValue()) {
            return -1;
        }
        if (o1.getTimeScaleUnitType().getCode().intValue() < o2.getTimeScaleUnitType().getCode()
                        .intValue()) {
            return 1;
        }

        return 0;

    }

    private int comparePriority(ReportDefinition o1, ReportDefinition o2) {
        if (o1.getDuration() > o2.getDuration()) {
            return -1;
        }
        if (o1.getDuration() < o2.getDuration()) {
            return 1;
        }
        return 0;
    }
}
