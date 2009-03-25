package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.utils.CollectionFilterer;
import gov.nih.nci.cabig.caaers.utils.Filterer;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

import com.semanticbits.rules.api.RuleAuthoringService;
import com.semanticbits.rules.brxml.RuleSet;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public class ListRuleCommand implements RuleInputCommand {

    private List<RuleSet> ruleSets;

    public ListRuleCommand(RuleAuthoringService ruleAuthoringService) throws RemoteException {
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
        // ruleSets.remove(0);//removing the default
    }

    public List<RuleSet> getRuleSets() {
        return ruleSets;
    }

    public void setRuleSets(List<RuleSet> ruleSets) {
        this.ruleSets = ruleSets;
    }
}
