package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

public class Arm  extends AbstractMutableDomainObject {
	private String name;
	private String descriptionText;
	
	public Arm(String name)
	{
      	this.name = name;	
	}
	public Arm(String name, String descriptionText)
	{
      	this.name = name;	
      	this.descriptionText = descriptionText;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescriptionText() {
		return descriptionText;
	}
	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}
	
}
