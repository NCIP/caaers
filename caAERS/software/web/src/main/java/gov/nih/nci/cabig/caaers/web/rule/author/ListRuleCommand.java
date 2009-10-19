package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.utils.CollectionFilterer;
import gov.nih.nci.cabig.caaers.utils.Filterer;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.semanticbits.rules.api.RuleAuthoringService;
import com.semanticbits.rules.brxml.RuleSet;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public class ListRuleCommand implements RuleInputCommand {

    private List<RuleSet> ruleSets;
    
    // Attributes related to import rule
    private String folder;
    private String message;
    private String errorMessage;
    
    private boolean updated = false;

    private MultipartFile ruleSetFile1;
    // Done with attributes related to import rule

    public ListRuleCommand(RuleAuthoringService ruleAuthoringService) throws RemoteException {
        populateRuleSets(ruleAuthoringService);
    }
    
    public void populateRuleSets(RuleAuthoringService ruleAuthoringService){
    	setRuleSets(null);
    	ruleSets = ruleAuthoringService.getAllRuleSets();
        Filterer filterer = new CollectionFilterer(ruleSets); 
        Iterator collectionIter = filterer.iterator();
        while (collectionIter.hasNext()) {
        	RuleSet ruleSet = (RuleSet)collectionIter.next();
        	if (ruleSet.getDescription().equals("The default rule package")) {
        		filterer.remove(ruleSet);
        	}
        }
        ruleSets = (List<RuleSet>) filterer.getFilteredObject();
    }

    public List<RuleSet> getRuleSets() {
        return ruleSets;
    }

    public void setRuleSets(List<RuleSet> ruleSets) {
        this.ruleSets = ruleSets;
    }
    
    // Getters and Setters related to import rule
    public MultipartFile getRuleSetFile1() {
        return ruleSetFile1;
    }

    public void setRuleSetFile1(MultipartFile ruleSetFile1) {
        this.ruleSetFile1 = ruleSetFile1;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    // Done with getters and setters related to import rule.
}
