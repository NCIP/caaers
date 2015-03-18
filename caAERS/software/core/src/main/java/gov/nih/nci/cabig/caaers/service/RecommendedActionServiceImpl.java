package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.ReportTableRow;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.dto.ApplicableReportDefinitionsDTO;
import gov.nih.nci.cabig.caaers.domain.dto.EvaluationResultDTO;
import gov.nih.nci.cabig.caaers.domain.dto.ReportDefinitionWrapper;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.utils.DurationUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.*;

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
    public void refreshApplicableReportTable(EvaluationResultDTO evaluationResult,Map<Integer, ExpeditedAdverseEventReport> aeReportIndexMap,Map<Integer, List<ReportTableRow>> applicableReportTableMap,ApplicableReportDefinitionsDTO applicableReportDefinitions){

        applicableReportTableMap.clear();

        //for every report id (including ZERO)
        for(Integer aeReportId : aeReportIndexMap.keySet()){

            //find the earliest graded date, used while evaluating the aes.
            //for the default data collection (which will be new)
            List<AdverseEvent> seriousAdverseEvents = evaluationResult.getSeriousAdverseEvents(aeReportId);
            Date updatedDate = null;
            Date gradedDate = null;
            if(CollectionUtils.isNotEmpty(seriousAdverseEvents)){
                updatedDate = AdverseEventReportingPeriod.findEarliestPostSubmissionUpdatedDate(seriousAdverseEvents);
                gradedDate = AdverseEventReportingPeriod.findEarliestGradedDate(seriousAdverseEvents);
            }else{
                List<AdverseEvent> applicableAdverseEvents = evaluationResult.getAllAeMap().get(aeReportId);
                updatedDate = AdverseEventReportingPeriod.findEarliestPostSubmissionUpdatedDate(applicableAdverseEvents);
                gradedDate = AdverseEventReportingPeriod.findEarliestGradedDate(applicableAdverseEvents);
            }

            if(updatedDate == null) updatedDate = new Date();
            if(gradedDate == null) gradedDate = new Date();


            //all report defs (load them as default)
            List<ReportDefinition> allReportDefs = applicableReportDefinitions.getReportDefinitions();
            Map<Integer, ReportTableRow> rowMap = new LinkedHashMap<Integer, ReportTableRow>();


            ExpeditedAdverseEventReport aeReport = aeReportIndexMap.get(aeReportId);
            Date baseDate =  gradedDate;

            List<Report> reportsToAmendList = new ArrayList<Report>();
            List<ReportDefinitionWrapper> createAndEditWrappers = new ArrayList<ReportDefinitionWrapper>(); //needed to filter less stringent reports.

            //create a map, consisting of report definitions
            for(ReportDefinition rd : allReportDefs){
                if(aeReport != null && aeReport.hasExistingReportsOfSameOrganizationAndType(rd)) {
                    baseDate = updatedDate;
                    reportsToAmendList.addAll(aeReport.findReportsToAmmend(rd));

                }
                ReportTableRow row  = ReportTableRow.createReportTableRow(rd, baseDate, ReportDefinitionWrapper.ActionType.CREATE);
                row.setAeReportId(aeReportId);

                row.setStatus("Not started");
                row.setOtherStatus("");
                row.setOtherDue("");
                row.setGrpStatus("");
                row.setGrpDue("");

                rowMap.put(rd.getId(), row);
            }

            //for each reports to amend, make sure, we have their group actions set to amend.
            for(Report report : reportsToAmendList){
                ReportTableRow row = rowMap.get(report.getReportDefinition().getId());
                if(row != null && row.getGrpAction() != ReportDefinitionWrapper.ActionType.AMEND){
                    row.setAction(ReportDefinitionWrapper.ActionType.AMEND);
                    row.setGrpAction(ReportDefinitionWrapper.ActionType.AMEND);
                    row.setStatus("Being amended");
                    row.setGrpStatus("Being amended");
                    row.setGrpDue("Submitted on " + DateUtils.formatDate(report.getSubmittedOn()));
                }
            }

            Set<ReportDefinitionWrapper> createWrappers = evaluationResult.getCreateMap().get(aeReportId);
            if(createWrappers != null){
                for(ReportDefinitionWrapper wrapper: createWrappers){
                    ReportTableRow row  = rowMap.get(wrapper.getDef().getId());
                    if(row == null) continue;
                    row.setPreSelected(true);
                    row.setGrpAction(null);
                    row.setOtherAction(null);
                    createAndEditWrappers.add(wrapper);
                }


            }

            Set<ReportDefinitionWrapper> editWrappers = evaluationResult.getEditMap().get(aeReportId);
            if(editWrappers != null){
                for(ReportDefinitionWrapper wrapper: editWrappers){
                    ReportTableRow row  = rowMap.get(wrapper.getDef().getId());
                    if(row == null) continue;
                    row.setPreSelected(true);

                    row.setAction(ReportDefinitionWrapper.ActionType.EDIT);
                    row.setGrpAction(ReportDefinitionWrapper.ActionType.WITHDRAW);
                    row.setOtherAction(ReportDefinitionWrapper.ActionType.WITHDRAW);

                    row.setStatus(wrapper.getStatus());
                    row.setGrpStatus("Being withdrawn");
                    row.setOtherStatus("Being withdrawn");

                    row.setDue(DurationUtils.formatDuration(wrapper.getDueOn().getTime() - new Date().getTime(), wrapper.getDef().getTimeScaleUnitType().getFormat()));
                    row.setGrpDue("");
                    row.setOtherDue("");
                    createAndEditWrappers.add(wrapper);
                }

            }

            Set<ReportDefinitionWrapper> withdrawWrappers = evaluationResult.getWithdrawalMap().get(aeReportId);
            if(withdrawWrappers != null){
                for(ReportDefinitionWrapper wrapper: withdrawWrappers){
                    ReportTableRow row  = rowMap.get(wrapper.getDef().getId());
                    if(row == null) continue;

                    row.setAction(ReportDefinitionWrapper.ActionType.EDIT);
                    row.setGrpAction(ReportDefinitionWrapper.ActionType.WITHDRAW);
                    row.setOtherAction(ReportDefinitionWrapper.ActionType.WITHDRAW);

                    row.setStatus(wrapper.getStatus());
                    row.setGrpStatus("Being withdrawn");
                    row.setOtherStatus("Being withdrawn");

                    row.setDue(DurationUtils.formatDuration(wrapper.getDueOn().getTime() - new Date().getTime(), wrapper.getDef().getTimeScaleUnitType().getFormat()));
                    row.setGrpDue("");
                    row.setOtherDue("");

                    //preselect the other one
                    if(wrapper.getSubstitute() != null){
                        ReportTableRow anotherRow = rowMap.get(wrapper.getSubstitute().getId());
                        if(anotherRow != null) anotherRow.setPreSelected(true);
                    }

                }
            }

            Set<ReportDefinitionWrapper> ammendWrappers = evaluationResult.getAmendmentMap().get(aeReportId);
            if(ammendWrappers != null){
                for(ReportDefinitionWrapper wrapper: ammendWrappers){
                    ReportTableRow row  = rowMap.get(wrapper.getDef().getId());
                    if(row == null) continue;

                    row.setAction(ReportDefinitionWrapper.ActionType.AMEND);
                    row.setGrpAction(ReportDefinitionWrapper.ActionType.AMEND);

                    row.setStatus("Being amended");
                    row.setGrpStatus("Being amended");
                    row.setOtherStatus("");

                    row.setGrpDue("Submitted on " + DateUtils.formatDate(wrapper.getSubmittedOn()));
                    row.setOtherDue("");

                    //preselect the other one
                    if(rowMap.get(wrapper.getSubstitute().getId()) != null){
                        rowMap.get(wrapper.getSubstitute().getId()).setPreSelected(true);
                    }
                }

            }

            Date now = DateUtils.today();
            for(ReportDefinitionWrapper wrapper: createAndEditWrappers){
                ReportDefinition rd = wrapper.getDef();
                String grp = "grp-" + rd.getOrganization().getId() + "-"+rd.getGroup().getId();
                Date expectedDue = rd.getExpectedDueDate(now);
                for(Map.Entry<Integer, ReportTableRow> rowEntry : rowMap.entrySet()){
                    if(rd.getId().equals(rowEntry.getKey())) continue; //ignore if it is the same report.
                    if(!StringUtils.equals(grp, rowEntry.getValue().getGroup())) continue; //ignore different group
                    Date actualDue = rowEntry.getValue().getReportDefinition().getExpectedDueDate(now);
                    if(DateUtils.compateDateAndTime(expectedDue, actualDue) < 0){
                        rowEntry.getValue().setStringent(false);
                    }


                }
            }


            applicableReportTableMap.put(aeReportId, new ArrayList<ReportTableRow>(rowMap.values()));
        }
    }

}
