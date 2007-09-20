package gov.nih.nci.cabig.caaers.domain.expeditedfields;

/**
 * @author Rhett Sutphin
 */
class ListPropertyNode extends PropertyNode {
    public ListPropertyNode(String propertyName) {
        super(propertyName);
    }

    @Override
    public boolean isList() {
        return true;
    }

    @Override
    protected StringBuilder getPropertyPath(StringBuilder target) {
        return super.getPropertyPath(target).append("[]");
    }

    @Override
    public String getDisplayName(int index) {
        return getDisplayNameCreator().createIndexedName(index);
    }
}
