package gov.nih.nci.cabig.caaers.web.admin;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import gov.nih.nci.cabig.caaers.domain.Study;

import gov.nih.nci.cabig.caaers.tools.configuration.ConfigurationProperty;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

/**
 * @author Rhett Sutphin
 */
public class MigrateStudiesCommand {
	
	private String fileName;
	private List<Study> studies = new ArrayList<Study>();

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<Study> getStudies() {
		return studies;
	}

	public void setStudies(List<Study> studies) {
		this.studies = studies;
	}
	
	

}
