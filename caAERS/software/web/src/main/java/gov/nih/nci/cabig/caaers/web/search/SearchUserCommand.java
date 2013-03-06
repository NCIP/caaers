/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.search;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * @author Monish
 *
 */
public class SearchUserCommand {
	
	private Integer organization;
    private boolean popupRequest;
    private String popupRequestType;

	public Integer getOrganization() {
		return organization;
	}

	public void setOrganization(Integer organization) {
		this.organization = organization;
	}

    public boolean isPopupRequest() {
        return popupRequest;
    }

    public void setPopupRequest(boolean popupRequest) {
        this.popupRequest = popupRequest;
    }

    public String getPopupRequestType() {
        return popupRequestType;
    }

    public void setPopupRequestType(String popupRequestType) {
        this.popupRequestType = popupRequestType;
    }

    public boolean isPersonAtrributesShown(){
        if(!popupRequest) return true;
        return StringUtils.equals(popupRequestType, "person");
    }

    public void setPersonAttributesShown(boolean b){
        //do nothing
    }

    public boolean isUserAttributesShown(){
       if(!popupRequest) return true;
       return StringUtils.equals(popupRequestType, "user");
    }
    public void setUserAttributesShown(boolean b){
        //do nothing.
    }

}
