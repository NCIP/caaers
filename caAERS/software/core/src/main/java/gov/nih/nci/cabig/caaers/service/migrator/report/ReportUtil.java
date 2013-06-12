/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.domain.OtherIntervention;

import java.util.List;

/**
 * User: medaV
 * Date: 6/12/13
 */
public class ReportUtil {
    public static OtherIntervention findActiveInterventionOnStudy(List<OtherIntervention> otherStudyRadiationList, OtherIntervention oi) {

        OtherIntervention result = null;
        for( OtherIntervention iter: otherStudyRadiationList) {
            if ( (iter.getName() != null && oi.getName() != null && iter.getName().equals(oi.getName()))) {
                result = iter;
                break;
            }
        }
        return result;
    }
}
