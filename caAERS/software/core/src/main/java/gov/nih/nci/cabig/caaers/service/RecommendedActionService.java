package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportTableRow;
import gov.nih.nci.cabig.caaers.domain.dto.EvaluationResultDTO;

import java.util.List;
import java.util.Map;

public interface RecommendedActionService {
    public void generateRecommendedReportTable(EvaluationResultDTO evaluationResult, Map<Integer, ExpeditedAdverseEventReport> aeReportIndexMap, Map<Integer, List<ReportTableRow>> recommendedReportTableMap);
}
