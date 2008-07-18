package gov.nih.nci.ccts.grid.common;

import javax.xml.namespace.QName;


public interface LabViewerConsumerConstants {
	public static final String SERVICE_NS = "http://grid.ccts.nci.nih.gov/LabViewerConsumer";
	public static final QName RESOURCE_KEY = new QName(SERVICE_NS, "LabViewerConsumerKey");
	public static final QName RESOURCE_PROPERTY_SET = new QName(SERVICE_NS, "LabViewerConsumerResourceProperties");

	//Service level metadata (exposed as resouce properties)
	public static final QName SERVICEMETADATA = new QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata", "ServiceMetadata");
	
}
