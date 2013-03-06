/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

 
/**
 * The Class AbstractExpeditedReportSingleChild.
 *
 * @author Rhett Sutphin
 */
@MappedSuperclass
public class AbstractExpeditedReportSingleChild extends AbstractMutableDomainObject implements
                ExpeditedAdverseEventReportChild {
    
    /** The report. */
    private ExpeditedAdverseEventReport report;

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReportChild#getReport()
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    public ExpeditedAdverseEventReport getReport() {
        return report;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReportChild#setReport(gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport)
     */
    public void setReport(ExpeditedAdverseEventReport report) {
        this.report = report;
    }
}
