package gov.nih.nci.cabig.caaers.dao.query;

import java.util.List;

public class StudySitesQuery extends AbstractQuery {

    public StudySitesQuery(String s) {
        super(s);
    }

    public StudySitesQuery() {
        super("SELECT so FROM StudyCoordinatingCenter so");
    }

    public void joinStudies() {
        join("so.study AS study");
    }

    public void joinStudyOrganizations() {
        join("study.studyOrganizations AS so2");
    }

    public void joinOrganizations() {
        join("so2.organization AS o");
    }

    public void filterBySuperOrganizationCode(List codes) {
        StringBuffer stringIDs = new StringBuffer();
        for (int i=0; i<codes.size(); i++) {
            stringIDs.append("'" + codes.get(i) + "', ");
        }
        if (stringIDs.length() > 0) stringIDs = new StringBuffer(stringIDs.substring(0, stringIDs.length() - 2));
        System.out.println("s=" + stringIDs);
        andWhere("so.organization.nciInstituteCode IN (" + stringIDs.toString() + ")");

    }

}