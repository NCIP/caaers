package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.web.rule.author.CreateRuleCommand;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;


public class AdvancedSearchCommand{
	
	private static final Log logger = LogFactory.getLog(AdvancedSearchCommand.class);
	private AdvancedSearchUi advancedSearchUi;
	private List<AdvancedSearchCriteriaParameter> criteriaParameters;
	private SearchTargetObject searchTargetObject;
	private String searchName;
	private String searchDescription;
	
	public AdvancedSearchCommand(){
		setAdvancedSearchUi();
	}
	
	public AdvancedSearchUi getAdvancedSearchUi(){
		return advancedSearchUi;
	}
	
	public List<AdvancedSearchCriteriaParameter> getCriteriaParameters(){
		if(criteriaParameters == null)
			criteriaParameters = new ArrayList<AdvancedSearchCriteriaParameter>();
		return criteriaParameters;
	}
	
	public void setCriteriaParameters(List<AdvancedSearchCriteriaParameter> criteriaParameters){
		this.criteriaParameters = criteriaParameters;
	}
	
	public void setSearchTargetObject(SearchTargetObject searchTargetObject){
		this.searchTargetObject = searchTargetObject;
	}
	
	public SearchTargetObject getSearchTargetObject(){
		return searchTargetObject;
	}
	
	public void setAdvancedSearchUi(){
        InputStream inputStream = Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("advancedSearch-ui.xml");

        Unmarshaller unmarshaller;
        try {
            unmarshaller = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.web.search.ui")
                            .createUnmarshaller();
            advancedSearchUi = (AdvancedSearchUi) unmarshaller.unmarshal(inputStream);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("Error in reading advancedSearch-ui xml file ");
            e.printStackTrace();
        }
	}

	/**
	 * @return the searchName
	 */
	public String getSearchName() {
		return searchName;
	}

	/**
	 * @param searchName the searchName to set
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	/**
	 * @return the searchDescription
	 */
	public String getSearchDescription() {
		return searchDescription;
	}

	/**
	 * @param searchDescription the searchDescription to set
	 */
	public void setSearchDescription(String searchDescription) {
		this.searchDescription = searchDescription;
	}
	
}