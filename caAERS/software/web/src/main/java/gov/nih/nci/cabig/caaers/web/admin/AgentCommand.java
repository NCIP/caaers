package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.*;

import java.util.ArrayList;
import java.util.List;

/*
 * @author Ion C. Olaru
 * 
 */
public class AgentCommand {

    private Agent agent;
    private Ctc ctcVersion;
    private MeddraVersion meddraVersion;

    private List<AgentSpecificTerm> agentSpecificTerms = new ArrayList<AgentSpecificTerm>();

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public List<AgentSpecificTerm> getAgentSpecificTerms() {
        return agentSpecificTerms;
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
}