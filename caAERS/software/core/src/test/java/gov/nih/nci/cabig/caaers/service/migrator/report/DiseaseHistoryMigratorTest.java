/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import junit.framework.TestCase;

/**
 * User: medav
 * Date: 1/28/13
 */
public class DiseaseHistoryMigratorTest extends AbstractTestCase {

	DiseaseHistoryMigrator migrator;
    ExpeditedAdverseEventReport src;
    ExpeditedAdverseEventReport dest;
    DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome;

    public void setUp() throws Exception {
        migrator = new DiseaseHistoryMigrator();
        src = new ExpeditedAdverseEventReport();
        dest = new ExpeditedAdverseEventReport();
        outcome = new DomainObjectImportOutcome<ExpeditedAdverseEventReport>();
        AdverseEventReportingPeriod rp = Fixtures.createReportingPeriod();
        Study study = rp.getStudy();
        study.setDiseaseTerminology(Fixtures.createDiseaseTerminology(study));
        Fixtures.createCtepStudyDisease(study, Fixtures.createDiseaseTerm("a", "b"));
        src.getDiseaseHistory().setAbstractStudyDisease(new CtepStudyDisease());
        src.getDiseaseHistory().getAbstractStudyDisease().setTerm(Fixtures.createDiseaseTerm("a", "b"));
        dest.setReportingPeriod(rp);
    }


    public void testMigrateWithValues() throws Exception {
        Date today  = DateUtils.today();
        src.getDiseaseHistory().setDiagnosisDate(today);
        migrator.migrate(src, dest, outcome);
        System.out.print(outcome.getValidationErrors());
        assertFalse(outcome.hasErrors());
        assertNotNull(dest.getDiseaseHistory().getAbstractStudyDisease());

    }
}
