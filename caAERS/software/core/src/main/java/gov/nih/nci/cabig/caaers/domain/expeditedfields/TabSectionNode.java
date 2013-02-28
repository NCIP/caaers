/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.expeditedfields;

 
/**
 * The Class TabSectionNode.
 *
 * @author Ion C. Olaru
 */
class TabSectionNode extends PropertylessNode {
    
    /** The section. */
    private TabSection section;

    /**
     * Instantiates a new tab section node.
     *
     * @param section the section
     */
    public TabSectionNode(TabSection section) {
        this.section = section;
        setDisplayNameCreator(new StaticDisplayNameCreator(section.name()));
    }

    /**
     * Gets the section.
     *
     * @return the section
     */
    public TabSection getSection() {
        return section;
    }
}
