package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.DeviceDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import org.easymock.classextension.EasyMock;

import java.util.ArrayList;
import java.util.List;

public class StudyDeviceMigratorTest extends AbstractTestCase {

	private DeviceDao deviceDao;
	private Study xmlStudy;
    private DomainObjectImportOutcome<Study> outcome;
    private Study dest;
	private StudyDeviceMigrator migrator;

	@Override
    protected void setUp() throws Exception {
		migrator = new StudyDeviceMigrator();
		xmlStudy = Fixtures.createStudy("StudyEvaluationPeriodsMigrator");
		dest = new LocalStudy();
		outcome = new DomainObjectImportOutcome<Study>();
		deviceDao = registerDaoMockFor(DeviceDao.class);
	}
}



