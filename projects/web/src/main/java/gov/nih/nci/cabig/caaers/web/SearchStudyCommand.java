package gov.nih.nci.cabig.caaers.web;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Kulasekaran
 */

public class SearchStudyCommand {
	protected final Log log = LogFactory.getLog(getClass());
	
	private List<SearchCommand> searchCommand;

	public List<SearchCommand> getSearchCommand() {
		return searchCommand;
	}

	public void setSearchCommand(List<SearchCommand> searchCommand) {
		this.searchCommand = searchCommand;
	}	    	
}

