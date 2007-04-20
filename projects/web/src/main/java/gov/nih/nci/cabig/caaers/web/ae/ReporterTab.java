package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;

/**
 * @author Kulasekaran
 */
public class ReporterTab extends AeTab {
    
	private ResearchStaffDao researchStaffDao;
	
    public ReporterTab() {
        super("Reporter info", "Reporter", "ae/reporter");
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map<String, InputFieldGroup> createFieldGroups(AdverseEventInputCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        return map;
    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();        
        refdata.put("staffRefData", getStaff());        
        return refdata;
    }
        
    @Override
    public boolean isAllowDirtyForward() {
        return false;
    }

    @Override
    protected void validate(
        AdverseEventInputCommand command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors
    ) {
    	
    }        
    
    private List<ResearchStaff> getStaff()
	{
		return researchStaffDao.getAll();  	
	}
    
	public ResearchStaffDao getResearchStaffDao() {
		return researchStaffDao;
	}

	@Required
	public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}       
}
