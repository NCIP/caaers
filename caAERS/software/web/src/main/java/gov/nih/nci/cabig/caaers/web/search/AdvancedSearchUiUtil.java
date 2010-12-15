package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.UiAttribute;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

/**
 * This is a utility class for AdvancedSearchUi.
 *
 * @author Sameer Sawant
 */
public final class AdvancedSearchUiUtil{

    private static final Log log = LogFactory.getLog(AdvancedSearchUiUtil.class);
    
    private AdvancedSearchUiUtil() {    	
    }

	public static SearchTargetObject getSearchTargetObjectByName(AdvancedSearchUi advancedSearchUi, String targetObjectName){
		for(SearchTargetObject stObject: advancedSearchUi.getSearchTargetObject())
			if(stObject.getClassName().equals(targetObjectName))
				return stObject;
		return null;
	}
	
	public static UiAttribute getUiAttributeByName(DependentObject dObject, String attributeName){
		for(UiAttribute attr: dObject.getUiAttribute())
			if(attr.getName().equals(attributeName))
				return attr;
		return null;
	}
	
	public static DependentObject getDependentObjectByName(SearchTargetObject stObject, String dependentObjectName){
		for(DependentObject dObject: stObject.getDependentObject())
			if(dObject.getClassName().equals(dependentObjectName))
				return dObject;
		return null;
	}
	
	public static DependentObject getDependentObjectByDisplayName(SearchTargetObject stObject, String dependentObjectDisplayName){
		for(DependentObject dObject: stObject.getDependentObject())
			if(dObject.getDisplayName().equals(dependentObjectDisplayName))
				return dObject;
		return null;
	}


    /**
     * Will parse the AdvancedSearchUi
     * @return
     */

	public static AdvancedSearchUi loadAdvancedSearchUi() throws CaaersSystemException{

        AdvancedSearchUi advancedSearchUi = null;

		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("advancedSearch-ui.xml");
        Unmarshaller unmarshaller;
		try {
			unmarshaller = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.web.search.ui").createUnmarshaller();
			advancedSearchUi = (AdvancedSearchUi) unmarshaller.unmarshal(inputStream);
		} catch (Exception e) {
			log.error(e);
            throw new CaaersSystemException("Unable to parse advancedSearch-ui.xml",e);
		}

        return advancedSearchUi;
	}

}