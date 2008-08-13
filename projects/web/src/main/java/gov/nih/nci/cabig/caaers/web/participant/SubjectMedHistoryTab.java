package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Biju Joseph
 *
 */
public class SubjectMedHistoryTab extends TabWithFields<ParticipantInputCommand> {
	
	//the below static variables corresponds to the field group names
	private static final String GENERAL = "general";
	private static final String PRIOR_THERAPY = "priorTherapy";
	
    private static final Log log = LogFactory.getLog(SubjectMedHistoryTab.class);

	
    private PriorTherapyDao priorTherapyDao;
    
    //static options of dropdowns are cached at Tab level. 
    Map<Object,Object> priorTherapyOptions;
    
	public SubjectMedHistoryTab() {
        super("Subject Medical History", "Subject Medical History", "par/par_subject_med_history");
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ParticipantInputCommand command) {
    	InputFieldGroupMap map = new  InputFieldGroupMap();

    	//selectively add the fields
    	String currentItem = command.getCurrentItem();
    	if(StringUtils.isEmpty(currentItem) || StringUtils.equals(currentItem, GENERAL)) initializeGeneralFieldGroup(command, map);
    	if(StringUtils.isEmpty(currentItem) || StringUtils.equals(currentItem, PRIOR_THERAPY)) initializePriorTherapyFieldGroup(command, map);
    	
    	return map;
    }
    
    private void initializeGeneralFieldGroup(ParticipantInputCommand command, InputFieldGroupMap map){
    	InputFieldGroup generalFieldGroup = new DefaultInputFieldGroup();
    	List<InputField> fields = generalFieldGroup.getFields();
    	map.put(GENERAL, generalFieldGroup);
    }
    
    /**
     * This method will create the field groups for the prior therapy screen.
     */
    private void initializePriorTherapyFieldGroup(ParticipantInputCommand command, InputFieldGroupMap map){
    	RepeatingFieldGroupFactory rfgFactory = new RepeatingFieldGroupFactory(PRIOR_THERAPY, "priorTherapies");
    	
        InputField priorTherapyField = InputFieldFactory.createSelectField("priorTherapy", "Prior therapy", false, fetchPriorTherapyOptions());
        InputField otherField = InputFieldFactory.createTextArea("other", "Comments", false);
        InputFieldAttributes.setColumns(otherField, 65);
        InputField startDateField = InputFieldFactory.createSplitDateField("startDate", "Therapy start Date", false, true, true, false);
        InputField endDateField = InputFieldFactory.createSplitDateField("endDate", "Therapy end date", false, true, true, false);
        
        rfgFactory.addField(priorTherapyField);
        rfgFactory.addField(otherField);
        rfgFactory.addField(startDateField);
        rfgFactory.addField(endDateField);
        
        map.addRepeatingFieldGroupFactory(rfgFactory, command.getAssignment().getPriorTherapies().size());
    }
    
    public ModelAndView createPriorTherapy(HttpServletRequest request , ParticipantInputCommand command, Errors error) {
		return null;
	}
    
    /**
     * Will initialize the Priortherapy drop down options
     * @return
     */
    private Map<Object, Object> fetchPriorTherapyOptions() {
    	if(priorTherapyOptions == null){
    		this.priorTherapyOptions = new LinkedHashMap<Object, Object>();
    		List<PriorTherapy> therapies = priorTherapyDao.getAll();
    		priorTherapyOptions.put("", "Please select");
    		if(therapies != null){
        		for(PriorTherapy therapy : therapies ){
        			priorTherapyOptions.put(therapy.getId(), therapy.getText());
        		}
        	}
    	}
        return priorTherapyOptions;
    }
    
    //TAB methods
    @Override
    public void onDisplay(HttpServletRequest request,ParticipantInputCommand command) {
    	//for non-ajax request, refresh the index fixed lists.
    	if(!StringUtils.equalsIgnoreCase(request.getParameter(getAjaxRequestParamName()), "true")){
    		command.refreshIndexFixedLists();
    	}
    }
    
    //OBJECT METHODS
    public PriorTherapyDao getPriorTherapyDao() {
		return priorTherapyDao;
	}
    public void setPriorTherapyDao(PriorTherapyDao priorTherapyDao) {
		this.priorTherapyDao = priorTherapyDao;
	}

}

