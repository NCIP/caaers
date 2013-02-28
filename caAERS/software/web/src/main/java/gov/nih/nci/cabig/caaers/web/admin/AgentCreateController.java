/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/*
* @author Ion C. Olaru
*
* */

public class AgentCreateController extends AgentController {

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        super.formBackingObject(request);
        AgentCommand c = new AgentCommand();
        c.setAgentSpecificTerms(new ArrayList<AgentSpecificTerm>());
        c.setAgent(new Agent());
        return c;
    }

    @Override
	public FlowFactory<AgentCommand> getFlowFactory() {
		return new FlowFactory<AgentCommand>() {
			public Flow<AgentCommand> createFlow(AgentCommand cmd) {
				Flow<AgentCommand> flow = new Flow<AgentCommand>("Agents");
				flow.addTab(new AgentTab<AgentCommand>("Agent ", "Agent", "admin/agentCreateForm"));
				return flow;
			}
		};
	}
    
    @Override
    protected void postProcessPage(HttpServletRequest request, Object command,
    		Errors errors, int page) throws Exception {
    	// TODO Auto-generated method stub
    	super.postProcessPage(request, command, errors, page);
    }

    @Override
    protected void validatePage(Object oCommand, Errors errors, int page, boolean finish) {
        AgentCommand c = (AgentCommand)oCommand;
        Agent a = agentDao.getByNscNumber(c.getAgent().getNscNumber());
        if (a != null)
            errors.reject("STU_012", new Object[] {String.format("Duplicate agent number '%s'", c.getAgent().getNscNumber())}, String.format("Duplicate agent number '%s'", c.getAgent().getNscNumber()));
        else
            super.validatePage(oCommand, errors, page, finish);
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest request,
    		HttpServletResponse response, Object command, BindException errors)
    		throws Exception {
    	AgentCommand agentCommand = (AgentCommand)command;
    	save(agentCommand,errors);
    	String url = "asaelEdit?agentID="+agentCommand.getAgent().getId()+"&showSuccessMessage=true";
    	if (agentCommand.getTerminology() != null){
    		url += "&terminologyName="+agentCommand.getTerminology()+
        			"&terminologyId="+ (agentCommand.getTerminology() == Term.CTC ? agentCommand.getCtcVersion().getId() : agentCommand.getMeddraVersion().getId());
    	}
    	return new ModelAndView("redirect:"+url);
    }

}
