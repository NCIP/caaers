package gov.nih.nci.cabig.caaers.rules.runtime;

import java.util.List;

public interface BusinessRulesExecutionService {

    public List<Object> fireRules(String bindingURI, List<Object> objects);
}
