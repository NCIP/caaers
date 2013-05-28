package gov.nih.nci.cabig.caaers.validation.validator;

import gov.nih.nci.cabig.caaers.api.impl.Helper;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.validation.ValidationError;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import gov.nih.nci.cabig.caaers.ws.faults.CaaersFault;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: vijendhar
 * Date: 4/3/13
 * Time: 1:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdverseEventValidatior {
    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private MessageSource messageSource;
    private int VERBATIM_MAX_SIZE = 65;

    private AdverseEvent validateAEUniqueness(AdverseEventReportingPeriod reportingPeriod,Study study,ValidationErrors errors) {
        boolean hasOtherMeddra = study.getOtherMeddra() != null;
        List<String> aeStringList = new ArrayList<String>();
        for (AdverseEvent ae : reportingPeriod.getAdverseEvents()) {
            if (ae.isRetired()) continue;

            // to support Verbatim First
            if (ae.getAdverseEventTerm().getTerm() == null) continue;

            StringBuffer key = new StringBuffer(String.valueOf(ae.getAdverseEventTerm().getTerm().getId()));

            if (ae.getAdverseEventTerm().isOtherRequired()) {
                if(hasOtherMeddra){
                    if (ae.getLowLevelTerm() == null) continue;
                    key.append(String.valueOf(ae.getLowLevelTerm().getId()));
                }else{
                    key.append(String.valueOf(ae.getDetailsForOther()));
                }
            }

            if (aeStringList.contains(key.toString())) return ae;
            aeStringList.add(key.toString());
        }
      return null;
    }

    private void validateOnlyOneDeath(AdverseEventReportingPeriod reportingPeriod,ValidationErrors errors){
        boolean foundGrade5 = false;
        for (AdverseEvent ae : reportingPeriod.getAdverseEvents()) {
            if (ae.isRetired()) continue;

            if(ae.getGrade() != null && ae.getGrade().equals(Grade.DEATH)){
              if (foundGrade5) {
                  addValidationError(errors, "WS_AEMS_080", messageSource.getMessage("WS_AEMS_080",
                          new String[] {}, "", Locale.getDefault()));
                  break;
              }
                foundGrade5 = true;
            }

        }
    }

    private void validateVerbatimMaxSize(AdverseEventReportingPeriod reportingPeriod,ValidationErrors errors){
        for (AdverseEvent ae : reportingPeriod.getAdverseEvents()) {
            if (ae.getDetailsForOther() != null && ae.getDetailsForOther().length() > VERBATIM_MAX_SIZE ) {
                String len = String.valueOf(ae.getDetailsForOther().length());
                addValidationError(errors,"WS_AEMS_081", messageSource.getMessage("WS_AEMS_081",
                        new String[] {String.valueOf(VERBATIM_MAX_SIZE)}, "", Locale.getDefault()) ) ;
                break;
            }
        }
    }

    private void addValidationError(ValidationErrors errors, String code, String message) {
        ValidationError e = new ValidationError();
        e.setCode(code);
        e.setMessage(message);
        errors.getErrors().add(e);

    }

    private void validateDateFirstLearned(AdverseEventReportingPeriod reportingPeriod,ValidationErrors errors) {
        for (AdverseEvent ae : reportingPeriod.getAdverseEvents()) {
            if (ae.getGradedDate().after(ae.getStartDate())) {
                addValidationError(errors,"CAE_021", messageSource.getMessage("CAE_021",
                        new String[] {}, "", Locale.getDefault()) ) ;
                break;
            }
        }

    }

    public void validate(AdverseEventReportingPeriod reportingPeriod, Study study, ValidationErrors errors){
        if ( study.getAeTermUnique()) {
            AdverseEvent adverseEvent = validateAEUniqueness(reportingPeriod, study, errors);
            if (adverseEvent != null) {
               String name = adverseEvent.getDisplayName();
               addValidationError(errors,"WS_AEMS_082", messageSource.getMessage("WS_AEMS_082",
                        new String[] {name}, "", Locale.getDefault()) ) ;
            }
        }
        validateOnlyOneDeath(reportingPeriod, errors);
        validateVerbatimMaxSize(reportingPeriod, errors);
        validateDateFirstLearned(reportingPeriod,errors);
    }

}
