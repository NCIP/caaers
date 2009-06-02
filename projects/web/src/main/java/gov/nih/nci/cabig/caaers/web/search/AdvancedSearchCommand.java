package gov.nih.nci.cabig.caaers.web.search;

import gov.nih.nci.cabig.caaers.web.rule.author.CreateRuleCommand;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;


public class AdvancedSearchCommand{
	
	private static final Log logger = LogFactory.getLog(AdvancedSearchCommand.class);
	private AdvancedSearchUi advancedSearchUi;
//	private SearchTargetObject searchTargetObject;
	
	public AdvancedSearchCommand(){
		setAdvancedSearchUi();
	}
	
	public AdvancedSearchUi getAdvancedSearchUi(){
		return advancedSearchUi;
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
	
}