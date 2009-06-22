package gov.nih.nci.cabig.caaers.web.study;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Kulasekaran
 */

public class SearchCommand {
    protected final Log log = LogFactory.getLog(getClass());

    private String searchTypeText;

    private String searchType;

    private String searchText;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchTypeText() {
        return searchTypeText;
    }

    public void setSearchTypeText(String searchTypeText) {
        this.searchTypeText = searchTypeText;
    }
}
