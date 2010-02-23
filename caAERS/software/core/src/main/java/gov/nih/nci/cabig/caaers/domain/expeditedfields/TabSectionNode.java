package gov.nih.nci.cabig.caaers.domain.expeditedfields;

/**
 * @author Ion C. Olaru
 */
class TabSectionNode extends PropertylessNode {
    private TabSection section;

    public TabSectionNode(TabSection section) {
        this.section = section;
        setDisplayNameCreator(new StaticDisplayNameCreator(section.name()));
    }

    public TabSection getSection() {
        return section;
    }
}