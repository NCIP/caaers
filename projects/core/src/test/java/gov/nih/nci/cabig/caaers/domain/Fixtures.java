package gov.nih.nci.cabig.caaers.domain;

/**
 * @author Rhett Sutphin
 */
public class Fixtures {
    public static <T extends DomainObject> T setId(int id, T target) {
        target.setId(id);
        return target;
    }

    public static Participant createParticipant(String first, String last) {
        Participant p = new Participant();
        p.setFirstName(first);
        p.setLastName(last);
        return p;
    }

    public static Study createStudy(String shortTitle) {
        Study s = new Study();
        s.setShortTitle(shortTitle);
        return s;
    }

    public static Site createSite(String name) {
        Site site = new Site();
        site.setName(name);
        return site;
    }

    public static StudyParticipantAssignment assignParticipant(Participant p, Study study, Site site) {
        StudySite ss = new StudySite();
        study.addStudySite(ss);
        site.addStudySite(ss);

        StudyParticipantAssignment assignment = new StudyParticipantAssignment();
        ss.addAssignment(assignment);
        p.addAssignment(assignment);

        return assignment;
    }
}
