/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
