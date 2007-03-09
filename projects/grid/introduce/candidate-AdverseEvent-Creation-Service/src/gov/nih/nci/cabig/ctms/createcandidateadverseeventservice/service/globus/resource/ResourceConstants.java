package gov.nih.nci.cabig.ctms.createcandidateadverseeventservice.service.globus.resource;

import javax.xml.namespace.QName;


public interface ResourceConstants {
	public static final String SERVICE_NS = "http://createcandidateadverseeventservice.ctms.cabig.nci.nih.gov/CreateCandidateAdverseEventService";
	public static final QName RESOURCE_KEY = new QName(SERVICE_NS, "CreateCandidateAdverseEventServiceKey");
	public static final QName RESOURCE_PROPERY_SET = new QName(SERVICE_NS, "CreateCandidateAdverseEventServiceResourceProperties");

	//Service level metadata (exposed as resouce properties)
	
}
