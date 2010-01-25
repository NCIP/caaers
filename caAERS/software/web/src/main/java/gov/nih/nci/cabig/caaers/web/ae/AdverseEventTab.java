package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.CtcGrade;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.CaaersFieldConfigurationManagerFactory;
import gov.nih.nci.cabig.caaers.web.fields.CompositeField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.fields.validators.FieldValidator;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;

/**
 * 
 * @author Sameer Sawant
 *
 */
public class AdverseEventTab extends TabWithFields<CaptureAdverseEventInputCommand>{
	
	protected AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	protected CtcTermDao ctcTermDao;
	protected static final Collection<Grade> GRADES = new ArrayList<Grade>(5);
	protected static final Collection<Hospitalization> HOSPITALIZATION = new ArrayList<Hospitalization>(2);
	protected EvaluationService evaluationService;
	protected ReportRepository reportRepository;
	protected ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
	protected CaaersFieldConfigurationManagerFactory caaersFieldConfigurationManagerFactory;
	
	static {
    	GRADES.addAll(Arrays.asList(Grade.values()));
        GRADES.remove(Grade.NORMAL);
        GRADES.remove(Grade.NOT_EVALUATED);
    }
	
	static {
		HOSPITALIZATION.addAll(Arrays.asList(Hospitalization.values()));
		HOSPITALIZATION.remove(Hospitalization.NONE);
	}
	
	public AdverseEventTab(String longTitle, String shortTitle, String viewName){
		super(longTitle, shortTitle, viewName);
	}
	
	@Override
	public Map<String, InputFieldGroup> createFieldGroups(CaptureAdverseEventInputCommand cmd) {
		Map<String, InputFieldGroup> map = new HashMap<String, InputFieldGroup>();
		return map;
	}
	
	public Map<Object, Object> fetchTreatmentAssignmentOptions(CaptureAdverseEventInputCommand cmd) {
		return WebUtils.collectOptions(cmd.getStudy().getTreatmentAssignments(), "id", "code", "Please select");
	}
	
	protected Map<Object, Object> createExpectedOptions() {
		Map<Object, Object> options = new LinkedHashMap<Object, Object>();
        options.put("", "Please select");
        options.put(Boolean.TRUE, "Yes");
        options.put(Boolean.FALSE, "No");
        return options;
    }
	
	protected Map<Object, Object> createSeriousOptions() {
		Map<Object, Object> options = new LinkedHashMap<Object, Object>();
        options.put("", "Please select");
        options.put(OutcomeType.CONGENITAL_ANOMALY.name(), OutcomeType.CONGENITAL_ANOMALY.getDisplayName());
        options.put(OutcomeType.DISABILITY.name(), OutcomeType.DISABILITY.getDisplayName());
        options.put(OutcomeType.LIFE_THREATENING.name(), OutcomeType.LIFE_THREATENING.getDisplayName());
        options.put(OutcomeType.REQUIRED_INTERVENTION.name(), OutcomeType.REQUIRED_INTERVENTION.getDisplayName());
        options.put(OutcomeType.OTHER_SERIOUS.name(), OutcomeType.OTHER_SERIOUS.getDisplayName());
        return options;
	}
	
	public CompositeField createTimeField(String baseProperty, String displayName){
    	InputField hrField = InputFieldFactory.createTextField("hourString", "", FieldValidator.HOUR_VALIDATOR);
    	InputField mmField = InputFieldFactory.createTextField("minuteString"," ", FieldValidator.MINUTE_VALIDATOR);
    	LinkedHashMap< Object, Object> amPmOption = new LinkedHashMap<Object, Object>();
    	amPmOption.put("0", "AM");
    	amPmOption.put("1", "PM");
    	InputField amPmField = InputFieldFactory.createSelectField("type", "",false, amPmOption);
    	InputFieldAttributes.setSize(hrField, 2);
    	InputFieldAttributes.setSize(mmField, 2);

    	return new CompositeField(baseProperty, new DefaultInputFieldGroup(null,displayName).addField(hrField).addField(mmField).addField(amPmField));
    }
	
	
	protected Map<Object, Object> createHospitalizationOptions() {
        Map<Object, Object> hospitalizationOptions = new LinkedHashMap<Object, Object>();
        hospitalizationOptions.put("", "Please select");
        hospitalizationOptions.putAll(WebUtils.collectOptions(HOSPITALIZATION, "name", "displayName"));
        return hospitalizationOptions;
    }
	
	protected Map<Object, Object> createAttributionOptions() {
        Map<Object, Object> attributionOptions = new LinkedHashMap<Object, Object>();
        attributionOptions.put("", "Please select");
        attributionOptions.putAll(WebUtils.collectOptions(Arrays.asList(Attribution.values()), "name", "displayName"));
        return attributionOptions;
    }
	
	protected Map<Object, Object> createGradeOptions(AdverseEvent ae, String terminology) {
		Map<Object, Object> gradeOptions = new LinkedHashMap<Object, Object>();
        
        //for solicited AEs always add NotEvaluated and Normal/Evaluated
        if(ae.getSolicited()){
        	gradeOptions.put(Grade.NOT_EVALUATED.getName(), Grade.NOT_EVALUATED.getDisplayName());
        	gradeOptions.put(Grade.NORMAL.getName(), Grade.NORMAL.getCode() + ":  " + Grade.NORMAL.getDisplayName());
        }
        if(terminology.equals("Ctc")){
        	List<CtcGrade> ctcGrades = (ae.getAdverseEventCtcTerm().getCtcTerm() == null) ? null: ae.getAdverseEventCtcTerm().getCtcTerm().getContextualGrades();
        	if(ctcGrades == null || ctcGrades.isEmpty()){
        		//no- add grades (1-5)
        		gradeOptions.putAll(WebUtils.collectCustomOptions(GRADES, "name", "code", "displayName", ":  "));
        	}else{
        		//if contextual grades are there , add it
        		gradeOptions.putAll(WebUtils.collectCustomOptions(ctcGrades, "name", "code", "displayName", ":  "));
        	}
        }else{
        	//always add the grades (1-5)
        	gradeOptions.putAll(WebUtils.collectCustomOptions(GRADES, "name", "code", "displayName", ":  "));
        }
            	
        return gradeOptions;
    }
	
	/**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final HttpServletRequest request, final String attributName) {

        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }
    
	
    public AdverseEventReportingPeriodDao getAdverseEventReportingPeriodDao() {
		return adverseEventReportingPeriodDao;
	}
    
    public void setAdverseEventReportingPeriodDao(
			AdverseEventReportingPeriodDao adverseEventReportingPeriodDao) {
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
	}
    
    public CtcTermDao getCtcTermDao() {
		return ctcTermDao;
	}
    
    public void setCtcTermDao(CtcTermDao ctcTermDao) {
		this.ctcTermDao = ctcTermDao;
	}
    
	public EvaluationService getEvaluationService() {
        return evaluationService;
    }

    public void setEvaluationService(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }
    
    public void setReportRepository(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
    
    public ExpeditedAdverseEventReportDao getExpeditedAdverseEventReportDao() {
		return expeditedAdverseEventReportDao;
	}
    public void setExpeditedAdverseEventReportDao(
			ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
		this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
	}
    
    @Required
	public void setCaaersFieldConfigurationManagerFactory(CaaersFieldConfigurationManagerFactory caaersFieldConfigurationManagerFactory){
		this.caaersFieldConfigurationManagerFactory = caaersFieldConfigurationManagerFactory;
	}
}