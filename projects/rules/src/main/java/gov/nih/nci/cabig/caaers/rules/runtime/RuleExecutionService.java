package gov.nih.nci.cabig.caaers.rules.runtime;

import gov.nih.nci.cabig.caaers.rules.domain.Study;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.rules.ObjectFilter;

@WebService(
        serviceName = "RuleExecutionService", endpointInterface = "gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService"
)
public interface RuleExecutionService {

	/**
	 * Fire all the rules in the RuleExecutionSet referred by the bindingUri.
	 * 
	 * */
	public abstract List<Study> fireRules(String bindingUri,
			List<Study> inputObjects) throws RemoteException;

	/**
	 * Fire all the rules in the RuleExecutionSet referred by the bindingUri.
	 * 
	 * */
	public abstract List<Study> fireRules(String bindingUri,
			List<Study> inputObjects, Map<String, Object> properties)
			throws RemoteException;

	/**
	 * Fire all the rules in the RuleExecutionSet referred by the bindingUri.
	 * 
	 * */
	public abstract void fireRules(String bindingUri,
			List<Study> inputObjects, ObjectFilter objectFilter)
			throws RemoteException;

	public abstract String fireRules(String bindUri);

}