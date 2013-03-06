/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule.author;

import com.semanticbits.rules.impl.RulesEngineServiceImpl;
import gov.nih.nci.cabig.caaers.domain.RuleSet;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;
import org.springframework.web.multipart.MultipartFile;

import java.rmi.RemoteException;
import java.util.List;


/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * @author Biju Joseph
 */
public class ListRuleCommand implements RuleInputCommand {

    private List<RuleSet> ruleSets;
    
    // Attributes related to import rule
    private String folder;
    private String message;
    private String errorMessage;
    
    private boolean updated = false;

    private MultipartFile ruleSetFile1;

    private CaaersRulesEngineService caaersRulesEngineService;
    // Done with attributes related to import rule

    public ListRuleCommand(CaaersRulesEngineService caaersRulesEngineService) throws RemoteException {
        this.caaersRulesEngineService = caaersRulesEngineService;
        populateRuleSets(caaersRulesEngineService);
    }
    
    public void populateRuleSets(CaaersRulesEngineService caaersRulesEngineService){
    	setRuleSets(null);
    	ruleSets = caaersRulesEngineService.getAllRuleSets();
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



    //------------------ only for debuging ---------------------
    public List<String> getAllFromSageArea(){
        return ((RulesEngineServiceImpl)caaersRulesEngineService.getRuleEngineService() ).getRuleRepository().listAllFromStageArea();
    }
    public List<String> getAllFromDeployArea(){
        return ((RulesEngineServiceImpl)caaersRulesEngineService.getRuleEngineService() ).getRuleRepository().listAllFromDeployArea();
    }

    public List<String> getAllFromRuntimeEngine(){
        return  ((RulesEngineServiceImpl)caaersRulesEngineService.getRuleEngineService() ).getRuleDeploymentService().listRegisterdRuleSets();
    }
}
