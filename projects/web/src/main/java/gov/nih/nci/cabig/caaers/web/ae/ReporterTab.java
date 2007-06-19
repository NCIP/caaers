package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

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
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        return new InputFieldGroupMap();
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
