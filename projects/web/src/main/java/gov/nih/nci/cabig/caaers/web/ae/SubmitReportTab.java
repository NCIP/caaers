package gov.nih.nci.cabig.caaers.web.ae;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

/**
 * @author Krikor Krumlian
 */
public class SubmitReportTab extends AeTab {

    public SubmitReportTab() {
        super("Submission", "Submit Report", "ae/submitReport");

    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
    	String reportIndex =  ((SubmitExpeditedAdverseEventCommand)command).getReportIndex() ;
    	if ( reportIndex == null ){
    		throw new CaaersSystemException("Report Index Not Defined");
    	}
        InputFieldGroupMap map = new InputFieldGroupMap();
        InputFieldGroup ccReport = new DefaultInputFieldGroup("ccReport");
        ccReport.getFields().add(
        		
				InputFieldFactory.createTextArea("aeReport.reports["
						+ reportIndex + "].lastVersion.email",
						"Cc"));
        map.addInputFieldGroup(ccReport);
        return map;
    }

    @Override
    protected void validate(
        ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors
    ) {
    	String reportIndex =  ((SubmitExpeditedAdverseEventCommand)command).getReportIndex();
    	String emailString = command.getAeReport().getReports().get(((int)Integer.parseInt(reportIndex))).getLastVersion().getEmail();
    	
    	if (emailString != null) {
    	String[] emails = emailString.split(",");
       
        for( String email : emails){
            
        	if (!isEmailValid(email)){
        		InputField field = fieldGroups.get("ccReport").getFields().get(0);
                errors.rejectValue(field.getPropertyName(), "NOT VALID", "Not Valid " + field.getDisplayName());
                break;
        	}
        }
        }
    }
    
    public boolean isEmailValid(String email){

        //Set the email pattern string
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

        //Match the given string with the pattern
        Matcher m = p.matcher(email.trim());

        //check whether match is found 
        boolean matchFound = m.matches();

        if (matchFound)
          return true;
        else
          return false;
          
      }

    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.SUBMIT_REPORT_SECTION;
    }
}
