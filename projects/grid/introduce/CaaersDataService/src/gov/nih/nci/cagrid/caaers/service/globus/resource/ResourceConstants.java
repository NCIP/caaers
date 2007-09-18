package gov.nih.nci.cagrid.caaers.service.globus.resource;

import javax.xml.namespace.QName;


public interface ResourceConstants {
	public static final String SERVICE_NS = "http://caaers.cagrid.nci.nih.gov/Caaers";
	public static final QName RESOURCE_KEY = new QName(SERVICE_NS, "CaaersKey");
	public static final QName RESOURCE_PROPERY_SET = new QName(SERVICE_NS, "CaaersResourceProperties");

	//Service level metadata (exposed as resouce properties)
	public static final QName SERVICEMETADATA_MD_RP = new QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata", "ServiceMetadata");
	public static final QName DOMAINMODEL_MD_RP = new QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.dataservice", "DomainModel");
	
}
