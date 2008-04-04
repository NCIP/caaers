package gov.nih.nci.cabig.caaers.web.utils;

import gov.nih.nci.cabig.caaers.utils.Lov;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class which provides Lovs  for testing purpose
 *
 * @author Biju Joseph
 */
public class LovHelper {

    public static List<Lov> getParticipantIdentifiersType() {

        List<Lov> lovs = new ArrayList<Lov>();
        Lov lov1 = new Lov("Medical Record Number", "Medical Record Number");
        Lov lov2 = new Lov("Other", "Other");
        lovs.add(lov1);
        lovs.add(lov2);
        return lovs;

    }

    public static List<Lov> getPhaseCodeRefData() {

        List<Lov> lovs = new ArrayList<Lov>();
        Lov lov1 = new Lov("I", "Phase I Trial");
        Lov lov2 = new Lov("I/II", "Phase I/II Trial");
        lovs.add(lov1);
        lovs.add(lov2);
        return lovs;

    }

    public static List<Lov> getStatusRefData() {

        List<Lov> lovs = new ArrayList<Lov>();
        Lov lov1 = new Lov("Active - Trial is open to accrual", "Active - Trial is open to accrual");
        lovs.add(lov1);
        return lovs;

    }
}
