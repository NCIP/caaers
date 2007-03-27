package gov.nih.nci.cabig.caaers.web.rule.notification.enums;

import gov.nih.nci.cabig.caaers.domain.CodedEnum;

/**
 * @author Sujith Vellat Thayyilthodi
 * */
public enum NotificationType implements CodedEnum {
	   EMAIL_NOTIFICATION(1, "Email Notification"),
	   FAX_NOTIFICATION(2, "Fax Notification"),
	   PAGER_NOTIFICATION(3, "Pager Notification"),
	   XML_NOTIFICATION(4, "XML Notification");

	   private int code;
	   private String name;
	   
	   
	   NotificationType(int code, String name) {
		   this.code = code;
		   this.name = name;
	   }


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getName() {
		return name();
	}


	public void setName(String name) {
		this.name = name;
	}

	

	public String getDisplayName() {
        return name == null ? titleCasedName(this) : name;
	}
	
    String titleCasedName(NotificationType instance) {
        StringBuilder name = new StringBuilder(instance.name().toLowerCase());
        name.replace(0, 1, name.substring(0, 1).toUpperCase());
        return name.toString();
    }
	   
}