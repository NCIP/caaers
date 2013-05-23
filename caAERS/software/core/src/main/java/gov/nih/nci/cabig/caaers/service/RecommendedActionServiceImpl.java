package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.ReportTableRow;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.dto.EvaluationResultDTO;
import gov.nih.nci.cabig.caaers.domain.dto.ReportDefinitionWrapper;
import gov.nih.nci.cabig.caaers.utils.DurationUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RecommendedActionServiceImpl implements RecommendedActionService {

    public void generateRecommendedReportTable(EvaluationResultDTO evaluationResult, Map<Integer, ExpeditedAdverseEventReport> aeReportIndexMap, Map<Integer, List<ReportTableRow>> recommendedReportTableMap) {

        //for every report id (including ZERO)
        for(Integer aeReportId : aeReportIndexMap.keySet()){

            //do for the default (new data collection).
            List<ReportTableRow> tableRows = new ArrayList<ReportTableRow>();

            //for the default data collection (which will be new)
            List<AdverseEvent> seriousAdverseEvents = evaluationResult.getSeriousAdverseEvents(aeReportId);
            Date updatedDate = null;
            Date gradedDate = null;
            if(CollectionUtils.isNotEmpty(seriousAdverseEvents)){
                updatedDate = AdverseEventReportingPeriod.findEarliestPostSubmissionUpdatedDate(seriousAdverseEvents);
                gradedDate = AdverseEventReportingPeriod.findEarliestGradedDate(seriousAdverseEvents);

            }

            if(updatedDate == null) updatedDate = new Date();
            if(gradedDate == null) gradedDate = new Date();

            //join the amend, withdraw, edit and create maps.
            List<ReportDefinitionWrapper> wrappers = evaluationResult.getJoinAllMaps(aeReportId);

            for(ReportDefinitionWrapper wrapper: wrappers){

                //if there is already a report created from the same group. use updated date.
                Date baseDate =  gradedDate;
                if(wrapper.getAction() == ReportDefinitionWrapper.ActionType.CREATE){
                    ExpeditedAdverseEventReport aeReport = aeReportIndexMap.get(aeReportId);
                    if(aeReport != null){
                        if(aeReport.hasExistingReportsOfSameOrganizationAndType(wrapper.getDef())){
                            baseDate = updatedDate;
                        }
                    }
                }

                ReportTableRow row  = ReportTableRow.createReportTableRow(wrapper.getDef(), baseDate, wrapper.getAction());
                row.setAeReportId(aeReportId);

                if(wrapper.getAction() == ReportDefinitionWrapper.ActionType.AMEND){
                    row.setStatus(wrapper.getStatus());
                    row.setDue("");
                }else if(wrapper.getAction() == ReportDefinitionWrapper.ActionType.WITHDRAW || wrapper.getAction() == ReportDefinitionWrapper.ActionType.EDIT) {
                    row.setDue(DurationUtils.formatDuration(wrapper.getDueOn().getTime() - new Date().getTime(), wrapper.getDef().getTimeScaleUnitType().getFormat()));
                    row.setStatus(wrapper.getStatus());
                }else {
                    row.setStatus(wrapper.getStatus());
                }

                tableRows.add(row);
            }
            recommendedReportTableMap.put(aeReportId, tableRows);
        }

    }

}
