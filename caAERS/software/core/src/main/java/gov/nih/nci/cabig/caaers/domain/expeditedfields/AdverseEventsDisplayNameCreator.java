package gov.nih.nci.cabig.caaers.domain.expeditedfields;

/**
 * @author Rhett Sutphin
 */
public class AdverseEventsDisplayNameCreator extends DefaultListDisplayNameCreator {
    public AdverseEventsDisplayNameCreator() {
        super("Adverse event");
    }

    @Override
    public String createIndexedName(int i) {
        if (i == 0) return "Primary adverse event";
        else return super.createIndexedName(i);
    }
}
