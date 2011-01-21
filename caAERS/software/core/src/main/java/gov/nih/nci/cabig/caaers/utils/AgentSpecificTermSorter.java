package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.domain.AgentSpecificCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import org.apache.axis.encoding.ser.ArrayDeserializer;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 1/20/11
 * Time: 11:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class  AgentSpecificTermSorter  implements Comparator<AgentSpecificTerm>  {

    public int compare(AgentSpecificTerm ast1, AgentSpecificTerm ast2) {
        String name1 = ast1 instanceof AgentSpecificCtcTerm ? ((AgentSpecificCtcTerm)ast1).getTerm().getTerm() : ((AgentSpecificMeddraLowLevelTerm)ast1).getTerm().getMeddraTerm();
        String name2 = ast2 instanceof AgentSpecificCtcTerm ? ((AgentSpecificCtcTerm)ast2).getTerm().getTerm() : ((AgentSpecificMeddraLowLevelTerm)ast2).getTerm().getMeddraTerm();
        return  name1.compareTo(name2);

    }

}

