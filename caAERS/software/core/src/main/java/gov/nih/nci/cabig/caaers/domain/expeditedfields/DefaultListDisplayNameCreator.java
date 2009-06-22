package gov.nih.nci.cabig.caaers.domain.expeditedfields;

/**
 * This implementation uses a base name like "Concomitant medication" and returns:
 * <ul>
 * <li><code>createIndexedName(2)</code>: "Concomitant medication 3"</li>
 * <li><code>createGenericName()</code>: "Concomitant medications"
 * </ul>
 * 
 * @author Rhett Sutphin
 */
public class DefaultListDisplayNameCreator implements DisplayNameCreator {
    private String baseName;

    public DefaultListDisplayNameCreator(String baseName) {
        this.baseName = baseName;
    }

    public String createIndexedName(int i) {
        return new StringBuilder(baseName).append(' ').append(i + 1).toString();
    }

    public String createGenericName() {
        return baseName + 's';
    }
}
