package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.LabCategory;
import gov.nih.nci.cabig.caaers.domain.LabTerm;
import gov.nih.nci.cabig.caaers.integration.schema.common.ActiveInactiveStatusType;
import gov.nih.nci.cabig.caaers.integration.schema.common.LabCategoryType;
import gov.nih.nci.cabig.caaers.integration.schema.common.LabTermType;

/**
 * @author Ramakrishna Gundala
 */
public class LabConverter{

    public static LabCategory convert(LabCategoryType labCategoryType) {
       LabCategory domainCategory = new LabCategory();
       domainCategory.setName(labCategoryType.getCategory());
       for(LabTermType labTermType :labCategoryType.getLabs().getLab()){
    	   domainCategory.addTerm(convert(labTermType));
       }
        if(labCategoryType.getStatus() != null){
        	domainCategory.setRetiredIndicator(labCategoryType.getStatus().equals(ActiveInactiveStatusType.ACTIVE) ? false:true);
		}
        return domainCategory;
    }

    public static LabTerm convert(LabTermType labTermType) {
        LabTerm domainTerm = new LabTerm();
        domainTerm.setTerm(labTermType.getTerm());
        if(labTermType.getStatus() != null){
        	domainTerm.setRetiredIndicator(labTermType.getStatus().equals(ActiveInactiveStatusType.ACTIVE) ? false:true);
		}
        return domainTerm;
    }

}
