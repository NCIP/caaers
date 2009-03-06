package gov.nih.nci.cabig.caaers.web.study;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Rhett Sutphin
 */
public class EmptyStudyTab extends StudyTab {
    public EmptyStudyTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }
    
    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, StudyCommand cmd) {
    	Map<String, Object> refdata = super.referenceData();
    	SolicitedEventTabTable table = new SolicitedEventTabTable(cmd.getStudy());
    	refdata.put("listOfSolicitedAERows", table.getListOfSolicitedAERows());  
    	return refdata;
    }
    

}
