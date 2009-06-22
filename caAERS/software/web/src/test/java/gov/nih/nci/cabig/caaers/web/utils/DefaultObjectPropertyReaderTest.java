package gov.nih.nci.cabig.caaers.web.utils;

import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.web.study.StudyCommand;
import junit.framework.TestCase;

/**
 * 
 * @author Biju Joseph
 *
 */
public class DefaultObjectPropertyReaderTest extends TestCase {
	DefaultObjectPropertyReader reader;
	protected void setUp() throws Exception {
		super.setUp();
		Study study = Fixtures.createStudy("test");
		Epoch e1 = Fixtures.createEpoch(1, "test");
		Epoch e2 = Fixtures.createEpoch(1, "test");
		study.addEpoch(e1);
		study.addEpoch(e2);
		StudyCommand command = new StudyCommand(null);
		command.setStudy(study);
		reader = new DefaultObjectPropertyReader(command, "study.activeEpochs[0].name");
	}

	public void testGetPropertyValueFromPath() throws Exception{
		System.out.println(reader.getPropertyValueFromPath());
	}

}
