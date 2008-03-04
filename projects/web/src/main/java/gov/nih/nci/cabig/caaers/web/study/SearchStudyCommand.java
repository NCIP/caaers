package gov.nih.nci.cabig.caaers.web.study;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kulasekaran
 */

public class SearchStudyCommand {

    private List<SearchCommand> searchCriteria = new ArrayList<SearchCommand>();

    public List<SearchCommand> getSearchCriteria() {
        return searchCriteria;
    }

    public void addSearchCriterion(SearchCommand criterion) {
        searchCriteria.add(criterion);
    }

    public void removeSearchCriterion(int index) {
        searchCriteria.remove(index);
    }

    public void setSearchCriteria(List<SearchCommand> searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}
