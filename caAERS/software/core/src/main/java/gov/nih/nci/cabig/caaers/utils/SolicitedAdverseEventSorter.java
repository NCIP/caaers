/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.domain.AbstractExpectedAE;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: nikhil
 * Date: 1/27/11
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class SolicitedAdverseEventSorter implements Comparator<SolicitedAdverseEvent> {

    public int compare(SolicitedAdverseEvent sAE1, SolicitedAdverseEvent sAE2) {
        String name1;
        String name2;

        name1 = sAE1.getCtcterm() != null ? sAE1.getCtcterm().getTerm() + (sAE1.isOtherRequired() && sAE1.getOtherTerm() != null ? sAE1.getOtherTerm().getMeddraTerm() : "") : sAE1.getLowLevelTerm().getMeddraTerm();
        name2 = sAE2.getCtcterm() != null ? sAE2.getCtcterm().getTerm() + (sAE2.isOtherRequired() && sAE2.getOtherTerm() != null ? sAE2.getOtherTerm().getMeddraTerm() : "") : sAE2.getLowLevelTerm().getMeddraTerm();

        return name1.compareTo(name2);
    }
}
