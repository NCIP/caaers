package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.caaers.domain.Term;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @author Ion C. Olaru
 * 
 */
public class AgentCommand {

    private Agent agent;

    private Term terminology;
    private Ctc ctcVersion;
    private MeddraVersion meddraVersion;

    private List<AgentSpecificTerm> agentSpecificTerms;
    
    private Map<Integer, AgentSpecificTerm> agentSpecificTermsExpectednessValuesSnapshot;

    public void save() {
        
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public List<AgentSpecificTerm> getAgentSpecificTerms() {
        return agentSpecificTerms;
    }

    public void setAgentSpecificTerms(List<AgentSpecificTerm> agentSpecificTerms) {
        this.agentSpecificTerms = agentSpecificTerms;
    }

    public Ctc getCtcVersion() {
        return ctcVersion;
    }

    public void setCtcVersion(Ctc ctcVersion) {
        this.ctcVersion = ctcVersion;
    }

    public MeddraVersion getMeddraVersion() {
        return meddraVersion;
    }

    public void setMeddraVersion(MeddraVersion meddraVersion) {
        this.meddraVersion = meddraVersion;
    }

    public Term getTerminology() {
        return terminology;
    }

    public void setTerminology(Term terminology) {
        this.terminology = terminology;
    }
    
    public void takeExpectednessSnapshot(){
    	agentSpecificTermsExpectednessValuesSnapshot = new HashMap<Integer, AgentSpecificTerm>();
    	for(AgentSpecificTerm agentSpecificTerm: agentSpecificTerms){
    		agentSpecificTermsExpectednessValuesSnapshot.put(agentSpecificTerm.getId(), takeSnapshot(agentSpecificTerm));
    	}
    }
    
    private AgentSpecificTerm takeSnapshot(AgentSpecificTerm agentSpecificTerm){
    	AgentSpecificTerm snapshot = new AgentSpecificTerm() {
			public boolean isOtherRequired() {
				return false;
			}
			public boolean isMedDRA() {
				return false;
			}
			public String getFullName() {
				return null;
			}
		};
		
		snapshot.setExpected(agentSpecificTerm.isExpected());
		snapshot.setExpectednessFrequency(agentSpecificTerm.getExpectednessFrequency());
		snapshot.setGrade1Frequency(agentSpecificTerm.getGrade1Frequency());
		snapshot.setGrade2Frequency(agentSpecificTerm.getGrade2Frequency());
		snapshot.setGrade3Frequency(agentSpecificTerm.getGrade3Frequency());
		snapshot.setGrade4Frequency(agentSpecificTerm.getGrade4Frequency());
		snapshot.setGrade5Frequency(agentSpecificTerm.getGrade5Frequency());
		
		return snapshot;
    }
    
    public boolean isUpdated(AgentSpecificTerm agentSpecificTerm){
    	if (agentSpecificTerm.getId() == null) return false;
		AgentSpecificTerm snapshot = agentSpecificTermsExpectednessValuesSnapshot.get(agentSpecificTerm.getId());
		if (snapshot == null) return false;
		if(expectednessEquals(agentSpecificTerm, snapshot)) return false;
    	return true;
    }
    
    private boolean expectednessEquals(AgentSpecificTerm agentSpecificTerm1, AgentSpecificTerm agentSpecificTerm2){
    	if( !(agentSpecificTerm1.isExpected()^agentSpecificTerm2.isExpected())
    		&&	checkDoubleEquals(agentSpecificTerm1.getExpectednessFrequency(), agentSpecificTerm2.getExpectednessFrequency())
    		&&	checkDoubleEquals(agentSpecificTerm1.getGrade1Frequency(), agentSpecificTerm2.getGrade1Frequency())
    		&&	checkDoubleEquals(agentSpecificTerm1.getGrade2Frequency(), agentSpecificTerm2.getGrade2Frequency())
    		&&	checkDoubleEquals(agentSpecificTerm1.getGrade3Frequency(), agentSpecificTerm2.getGrade3Frequency())
    		&&	checkDoubleEquals(agentSpecificTerm1.getGrade4Frequency(), agentSpecificTerm2.getGrade4Frequency())
    		&&	checkDoubleEquals(agentSpecificTerm1.getGrade5Frequency(), agentSpecificTerm2.getGrade5Frequency())
    			){
    		return true;
    	}
    	return false;
    }
    
    private boolean checkDoubleEquals(Double a, Double b){
    	if(a == null ^ b == null) return false;
    	if(a == null && b == null) return true;
    	if(a.equals(b)) return true;
    	return false;
    }
}