package gov.nih.nci.cabig.caaers.validation.annotation;

import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Organization;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

/**
 * @author Biju Joseph, Created on December,6th, 2007
 */
public class UniqueEmailAddressForResearchStaffValidator implements
                Validator<UniqueEmailAddressForResearchStaff> {

    String message;

    private ResearchStaffDao researchStaffDao;

    public boolean validate(final Object value) {
        if (value instanceof String) {

            ResearchStaffQuery researchStaffQuery = new ResearchStaffQuery();
            researchStaffQuery.filterByEmailAddress((String) value);
            List<ResearchStaff> researchStaffList = researchStaffDao
                            .searchResearchStaff(researchStaffQuery);
            return (researchStaffList == null || researchStaffList.isEmpty()) ? true : false;
        }
        return true;
    }

    public void initialize(UniqueEmailAddressForResearchStaff parameters) {
        message = parameters.message();

    }

    public String message() {
        return message;
    }

    @Required
    public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
        this.researchStaffDao = researchStaffDao;
    }
}