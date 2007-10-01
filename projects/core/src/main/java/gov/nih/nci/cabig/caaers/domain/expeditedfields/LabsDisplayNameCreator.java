package gov.nih.nci.cabig.caaers.domain.expeditedfields;

/**
 * @author Rhett Sutphin
 */
public class LabsDisplayNameCreator implements DisplayNameCreator {
    public String createGenericName() {
        return "Labs";
    }

    public String createIndexedName(int i) {
        return "Lab " + ((char) (i + 'A'));
    }
}
