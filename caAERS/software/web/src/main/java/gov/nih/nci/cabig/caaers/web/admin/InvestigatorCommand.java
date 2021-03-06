/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.security.authorization.domainobjects.User;

/**
 * @author: Biju Joseph
 */
public class InvestigatorCommand {

    private Investigator investigator;

    protected User csmUser;

    protected boolean shouldSync;
    protected boolean canSync;

    public Investigator getInvestigator() {
        return investigator;
    }

    public void setInvestigator(Investigator investigator) {
        this.investigator = investigator;
    }



    public boolean isCreateFlow(){
       return investigator == null || investigator.getId() == null;
    }

    public User getCsmUser() {
        return csmUser;
    }

    public void setCsmUser(User csmUser) {
        this.csmUser = csmUser;
    }

    public boolean isShouldSync() {
        return shouldSync;
    }

    public void setShouldSync(boolean shouldSync) {
        this.shouldSync = shouldSync;
    }

    public boolean isCanSync() {
        return canSync;
    }

    public void setCanSync(boolean canSync) {
        this.canSync = canSync;
    }

    /**
     * If true, in create mode we could continue the CSM operation.
     */
    public boolean canProceedCSMOperation(){
        if(csmUser == null || csmUser.getUserId() == null) return true;
        return canSync;
    }
}
