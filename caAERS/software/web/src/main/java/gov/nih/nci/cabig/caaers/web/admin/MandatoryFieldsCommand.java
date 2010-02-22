package gov.nih.nci.cabig.caaers.web.admin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import gov.nih.nci.cabig.caaers.dao.CaaersFieldDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.CaaersFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.web.ae.AdverseEventCaptureTab;

/**
 * This is the command class for MandatoryFieldsController 
 * @author Sameer Sawant
 * @author Ion C. Olaru
 */
public class MandatoryFieldsCommand {
	private List<CaaersFieldDefinition> mandatoryFields;
	private CaaersFieldDefinitionDao caaersFieldDefinitionDao;
	private Map<String, Integer> mandatoryFieldMap;
	
	public Map<String, Integer> getMandatoryFieldMap() {
		return mandatoryFieldMap;
	}

	public void setMandatoryFieldMap(Map<String, Integer> mandatoryFieldMap) {
		this.mandatoryFieldMap = mandatoryFieldMap;
	}

	public MandatoryFieldsCommand(CaaersFieldDefinitionDao caaersFieldDefinitionDao){
		this.caaersFieldDefinitionDao = caaersFieldDefinitionDao;
        mandatoryFields = caaersFieldDefinitionDao.getAll();
	}

    public void initializeMandatoryFieldMap() {
	        mandatoryFieldMap = new LinkedHashMap<String, Integer>();
	        if(CollectionUtils.isNotEmpty(mandatoryFields)){
	        	String path;
	            int i = 0;
	            for (CaaersFieldDefinition cfd : mandatoryFields) {
	                path = cfd.getFieldPath();
	                mandatoryFieldMap.put(path, i);
	                i++;
	            }
	        }
	    }
	 
	public void setMandatoryFields(List<CaaersFieldDefinition> mandatoryFields){
		this.mandatoryFields = mandatoryFields;
	}
	
	public List<CaaersFieldDefinition> getMandatoryFields(){
		return mandatoryFields;
	}
}