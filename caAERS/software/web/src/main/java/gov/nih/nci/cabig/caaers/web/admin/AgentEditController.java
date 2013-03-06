/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import gov.nih.nci.cabig.caaers.utils.AgentSpecificTermSorter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/*
* @author Ion C. Olaru
* 
* */

public class AgentEditController extends AgentController {

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        super.formBackingObject(request);

        Agent agent = null;
        agent = agentRepository.getAgentByID(Integer.parseInt(request.getParameter("agentID")));

        AgentCommand c = new AgentCommand();
        c.setAgent(agent);
        c.setAgentSpecificTerms(new ArrayList<AgentSpecificTerm>());
        List<AgentSpecificTerm> agentSpecificTerms = new ArrayList<AgentSpecificTerm>();
        agentSpecificTerms =    service.getListByAgent(agent.getId());
        Collections.sort(agentSpecificTerms,new AgentSpecificTermSorter());
        c.getAgentSpecificTerms().addAll(agentSpecificTerms);

        // need to determine the category of the first element in the previous list
        if (c.getAgentSpecificTerms().size() > 0) {
            AgentSpecificTerm at = c.getAgentSpecificTerms().get(0);
            if (at instanceof AgentSpecificCtcTerm) {
                AgentSpecificCtcTerm t = (AgentSpecificCtcTerm)at;
                c.setTerminology(Term.CTC);
                c.setCtcVersion(ctcDao.getById(t.getTerm().getCategory().getCtc().getId()));
                // c.setMeddraVersion(meddraVersionDao.getById(10));
            } else {
                AgentSpecificMeddraLowLevelTerm t = (AgentSpecificMeddraLowLevelTerm)at;
                c.setTerminology(Term.MEDDRA);
                c.setMeddraVersion(meddraVersionDao.getById(t.getTerm().getMeddraVersion().getId()));
            }
        } else {
            
        }

        return c;
    }

    @Override
	public FlowFactory<AgentCommand> getFlowFactory() {
		return new FlowFactory<AgentCommand>() {
			public Flow<AgentCommand> createFlow(AgentCommand cmd) {
				Flow<AgentCommand> flow = new Flow<AgentCommand>("Agents");
				flow.addTab(new AgentTab<AgentCommand>("Agent ", "Agent", "admin/agentEditForm"));
				return flow;
			}
		};
	}

}
