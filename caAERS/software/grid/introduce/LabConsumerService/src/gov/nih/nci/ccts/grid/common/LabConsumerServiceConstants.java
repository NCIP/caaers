package gov.nih.nci.ccts.grid.common;

import javax.xml.namespace.QName;


public interface LabConsumerServiceConstants {
	public static final String SERVICE_NS = "http://grid.ccts.nci.nih.gov/LabConsumerService";
	public static final QName RESOURCE_KEY = new QName(SERVICE_NS, "LabConsumerServiceKey");
	public static final QName RESOURCE_PROPERTY_SET = new QName(SERVICE_NS, "LabConsumerServiceResourceProperties");

	//Service level metadata (exposed as resouce properties)
	
}
