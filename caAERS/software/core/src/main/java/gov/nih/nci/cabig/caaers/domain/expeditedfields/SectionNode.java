package gov.nih.nci.cabig.caaers.domain.expeditedfields;

/**
 * @author Rhett Sutphin
 */
class SectionNode extends PropertylessNode {
    private ExpeditedReportSection section;

    public SectionNode(ExpeditedReportSection section) {
        this.section = section;
        // section.name() is legacy support. TODO: why not section.displayName?
        setDisplayNameCreator(new StaticDisplayNameCreator(section.name()));
    }

    public ExpeditedReportSection getSection() {
        return section;
    }
}
