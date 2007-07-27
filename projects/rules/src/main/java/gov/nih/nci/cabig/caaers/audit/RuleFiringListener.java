package gov.nih.nci.cabig.caaers.audit;

import org.drools.WorkingMemory;
import org.drools.event.ActivationCancelledEvent;
import org.drools.event.ActivationCreatedEvent;
import org.drools.event.AfterActivationFiredEvent;
import org.drools.event.AgendaEventListener;
import org.drools.event.BeforeActivationFiredEvent;

public class RuleFiringListener implements AgendaEventListener{
	private RuleLogger logger;
	public RuleFiringListener() throws Exception{
		try {
			logger = RuleLogger.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	 

	public void activationCancelled(ActivationCancelledEvent arg0, WorkingMemory arg1) {
		// TODO Auto-generated method stub
		
	}

	public void activationCreated(ActivationCreatedEvent arg0, WorkingMemory arg1) {
		// TODO Auto-generated method stub
		logger.logMessage("Condition Met");
	}

	public void afterActivationFired(AfterActivationFiredEvent afterActivationFiredEvent, WorkingMemory arg1) {
		if(afterActivationFiredEvent.getActivation().getRule()!=null){
			logger.logMessage("Rule Name:"+afterActivationFiredEvent.getActivation().getRule().getName());
		}else{
			logger.logMessage("Rule was null");
		}
		logger.logMessage("After event fired");
		// TODO Auto-generated method stub
		//afterActivationFiredEvent.toString();
		//logger.logMessage("Rule Name:"+afterActivationFiredEvent.getActivation().getRule().getName()+"| after Firing the rule"+afterActivationFiredEvent.getActivation().getRule().getPackage().toString());
	}

	public void beforeActivationFired(BeforeActivationFiredEvent beforeActivationFiredEvent, WorkingMemory arg1) {
		if(beforeActivationFiredEvent.getActivation().getRule()!=null){
			logger.logMessage("Rule Name:"+beforeActivationFiredEvent.getActivation().getRule().getName());
		}else{
			logger.logMessage("Rule was null");
		}
		
		logger.logMessage("Before event fired");
		// TODO Auto-generated method stub
		//beforeActivationFiredEvent.toString();
		//logger.logMessage("Rule Name:"+beforeActivationFiredEvent.getActivation().getRule().getName()+"| before Firing the rule"+beforeActivationFiredEvent.getActivation().getRule().getPackage().toString());
	}

}

