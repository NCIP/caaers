package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.dto.ReportDefinitionWrapper;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEvents;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

/**
 * This tab represents the Review and Report page in CaptureAdverseEvent flow.
 * 
 * @author Biju Joseph
 *
 */
public class ReviewAndReportTab extends AdverseEventTab {
	
	private static final Log log = LogFactory.getLog(ReviewAndReportTab.class);
	
	public ReviewAndReportTab() {
		super("Review and Report", "Review and Report", "ae/ae_review_and_report");
	}
	
	@Override
	public Map<String, Object> referenceData(HttpServletRequest request,CaptureAdverseEventInputCommand command) {
		
		Map<String, Object> refData = super.referenceData(request, command);
		
		//initialize outcomes if necessary
		int previousPage = WebUtils.getPreviousPage(request);
		int targetPage = WebUtils.getTargetPage(request);
		if(previousPage == 0 && targetPage == 2){
			command.initializeOutcomes();
		}
		
		//find the applicable report definitions(only once per page flow)
		command.findApplicableReportDefinitions();
		
		//find the evaluation result (always)
		command.evaluateSAERules();
		
		//generate the rules engine message for each data collection
		command.generateReadableRulesMessage();
		
		//refresh ae index map
		command.refreshAeReportIdIndex();
		
		//generate the report table data structure.
		command.refreshRecommendedReportTable();
		command.refreshApplicableReportTable();
		
		
		return refData;
	}
	
	@Override
	public void onBind(HttpServletRequest request,CaptureAdverseEventInputCommand command, Errors errors) {
		super.onBind(request, command, errors);
		ReviewAndReportResult reviewResult = new ReviewAndReportResult();
		command.setReviewResult(reviewResult);
		
		//set the reporting period id.
		reviewResult.setReportingPeriodId(command.getAdverseEventReportingPeriod().getId());
		
		Map<Integer, ReportDefinition> rdMap = command.getApplicableReportDefinitions().getReportDefinitionMap();
		
		try {
			//find the aeReport
			Integer aeReportId = ServletRequestUtils.getIntParameter(request, "activeAeReportId");
			reviewResult.setAeReportId(aeReportId);
			
			//bind the start dates
			List<AdverseEvent> adverseEvents  = command.getEvaluationResult().getAllAeMap().get(aeReportId);
			int size = adverseEvents.size();
			for(int i = 0; i < size; i++){
				AdverseEvent ae = adverseEvents.get(i);
				String startDateParamName = "evaluationResult.allAeMap[" + aeReportId.intValue() +"][" + i + "].startDate";
				if(WebUtils.hasParameter(request,startDateParamName)){
					String strStartDate = request.getParameter(startDateParamName);
					if(StringUtils.isNotEmpty(strStartDate)){
						ae.setStartDate(DateUtils.parseDateString(strStartDate).toDate());
					}else{
						ae.setStartDate(null);
					}
					
				}
			}
			
			String paramName =  "rd_" + aeReportId.toString();
			
			//selected report definition ids
			int[] selectedRdIds = ServletRequestUtils.getIntParameters(request, paramName + "_checked");
			
			//all report definition ids
			int[] rdIds = ServletRequestUtils.getIntParameters(request, paramName);
			
			for(int rdId : rdIds){
				
				//fetch the manual selection indicator
				String strManualIndicator = ServletRequestUtils.getStringParameter(request, paramName + "_" + rdId + "_manual" );
				reviewResult.getManualSelectionIndicatorMap().put(new Integer(rdId), StringUtils.equals("1", strManualIndicator));
				
				//actual action against each.
				String actualActionName = ServletRequestUtils.getStringParameter(request, paramName + "_" + rdId + "_actualaction");
				
				
				if(StringUtils.equalsIgnoreCase(ReportDefinitionWrapper.ActionType.AMEND.name(), actualActionName)){
					reviewResult.getAmendList().add(rdMap.get(rdId));
					//special case, amend with itself
					if(ArrayUtils.contains(selectedRdIds, rdId)){
						reviewResult.getCreateList().add(rdMap.get(rdId));
					}
				}
				
				if(StringUtils.equalsIgnoreCase(ReportDefinitionWrapper.ActionType.WITHDRAW.name(), actualActionName)){
					reviewResult.getWithdrawList().add(rdMap.get(rdId));
				}
				
				if(StringUtils.equalsIgnoreCase(ReportDefinitionWrapper.ActionType.EDIT.name(), actualActionName)){
					reviewResult.getEditList().add(rdMap.get(rdId));
				}
				
				if(StringUtils.equalsIgnoreCase(ReportDefinitionWrapper.ActionType.CREATE.name(), actualActionName)){
					reviewResult.getCreateList().add(rdMap.get(rdId));
				}
			}
			
			
			//now find the ae's selected. 
			int[] aeIds = ServletRequestUtils.getIntParameters(request, "ae_"+ aeReportId);
			for(int aeId : aeIds){
				reviewResult.getAeList().add(aeId);
			}
			
			//find primaryAE
			int primaryAEId = ServletRequestUtils.getIntParameter(request,"ae_" + aeReportId + "_primary");
			reviewResult.setPrimaryAdverseEventId(primaryAEId);
			
			//find the aes, deselected. 
			for(AdverseEvent ae: command.getEvaluationResult().getAllAeMap().get(aeReportId)){
				if(ArrayUtils.contains(aeIds, ae.getId().intValue())) continue;
				reviewResult.getUnwantedAEList().add(ae.getId());
			}
			
		} catch (ServletRequestBindingException e) {
			log.warn("Error while binding review-and-report page parameters, this is okay sometimes as we click back from reivew page", e);
		}
	}
	
	@Override
	protected void validate(CaptureAdverseEventInputCommand command,BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
		// TODO Auto-generated method stub
		super.validate(command, commandBean, fieldGroups, errors);
	}
	
}
