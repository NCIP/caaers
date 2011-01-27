package gov.nih.nci.cabig.caaers.utils;
import gov.nih.nci.cabig.caaers.domain.AbstractExpectedAE;
import gov.nih.nci.cabig.caaers.domain.ExpectedAECtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpectedAEMeddraLowLevelTerm;


import gov.nih.nci.cabig.caaers.domain.AgentSpecificCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 1/20/11
 * Time: 11:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExpectedAETermSorter implements Comparator<AbstractExpectedAE>  {

    public int compare(AbstractExpectedAE ast1, AbstractExpectedAE ast2) {

        String name1;
        String name2;

        if (ast1 instanceof ExpectedAECtcTerm) {
            ExpectedAECtcTerm ctcTerm = (ExpectedAECtcTerm)ast1;
            name1 = ctcTerm.getTerm().getTerm();
            if (ctcTerm.isOtherRequired()) name1 = name1 + (ctcTerm.getOtherMeddraTerm() != null ? ctcTerm.getOtherMeddraTerm().getMeddraTerm() : "");
        } else {
            ExpectedAEMeddraLowLevelTerm otherTerm = (ExpectedAEMeddraLowLevelTerm)ast1;
            name1 = otherTerm.getTerm().getMeddraTerm();
        }

        if (ast2 instanceof ExpectedAECtcTerm) {
            ExpectedAECtcTerm ctcTerm = (ExpectedAECtcTerm)ast2;
            name2 = ctcTerm.getTerm().getTerm();
            if (ctcTerm.isOtherRequired()) name2 = name2 + (ctcTerm.getOtherMeddraTerm() != null ? ctcTerm.getOtherMeddraTerm().getMeddraTerm() : "") ;
        } else {
            ExpectedAEMeddraLowLevelTerm otherTerm = (ExpectedAEMeddraLowLevelTerm)ast2;
            name2 = otherTerm.getTerm().getMeddraTerm();
        }

        return  name1.compareTo(name2);
    }

}

