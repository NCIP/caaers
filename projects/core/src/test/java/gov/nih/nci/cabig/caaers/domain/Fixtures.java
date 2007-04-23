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

    /** Creates an assignment and the associated Study, Participant, StudySite, and Site objs */
    public static StudyParticipantAssignment createAssignment() {
        return assignParticipant(
            createParticipant("D", "C"),
            createStudy("DC"),
            createSite("N/A")
        );
    }

    public static StudyParticipantAssignment assignParticipant(Participant p, Study study, Site site) {
        StudySite ss = new StudySite();
        ss.setSite(site);
        study.addStudySite(ss);
        site.addStudySite(ss);

        StudyParticipantAssignment assignment = new StudyParticipantAssignment();
        ss.addAssignment(assignment);
        p.addAssignment(assignment);

        return assignment;
    }

    public static StudyAgent createStudyAgent(String agentName) {
        StudyAgent agent = new StudyAgent();
        agent.setAgent(new Agent());
        agent.getAgent().setName(agentName);
        return agent;
    }
}
