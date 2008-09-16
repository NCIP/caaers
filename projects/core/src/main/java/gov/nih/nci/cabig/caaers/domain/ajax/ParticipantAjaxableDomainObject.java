package gov.nih.nci.cabig.caaers.domain.ajax;


/**
 *
 */
public class ParticipantAjaxableDomainObject extends AbstractAjaxableDomainObject {


    private String lastName;

    private String firstName;
    private String primaryIdentifierValue;

    //FIXME : this logic belongs to participant
    public String getDisplayName() {

        String primaryIdentifier = this.getPrimaryIdentifierValue() == null ? "" :
                " ( " + this.getPrimaryIdentifierValue() + " ) ";
        StringBuilder name = new StringBuilder();
        boolean hasLastName = getLastName() != null;
        if (getFirstName() != null) {
            name.append(getFirstName());
            if (hasLastName) {
                name.append(' ');
            }
        }
        if (hasLastName) {
            name.append(getLastName());
        }
        return name.toString() + primaryIdentifier;

    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {

        return lastName;
    }

    public String getPrimaryIdentifierValue() {
        return primaryIdentifierValue;
    }

    public void setPrimaryIdentifierValue(String primaryIdentifierValue) {
        this.primaryIdentifierValue = primaryIdentifierValue;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}