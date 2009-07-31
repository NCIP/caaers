package gov.nih.nci.cabig.caaers.tools.editors;

import edu.emory.mathcs.backport.java.util.Arrays;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Study;
import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class AbstractStudyDiseaseEditorTest extends TestCase {
	
	AbstractStudyDiseaseEditor editor;
	CtepStudyDisease c1 ;
	protected void setUp() throws Exception {
		super.setUp();
		Study study = Fixtures.createStudy("test");
		DiseaseTerm term = new DiseaseTerm();
		c1 = Fixtures.createCtepStudyDisease(study, term);
		c1.setId(5);
		editor = new AbstractStudyDiseaseEditor(Arrays.asList(new CtepStudyDisease[]{c1}));
	}

	public void testGetAsText() {
		editor.setValue(c1);
		String text = editor.getAsText();
		assertEquals("5", text);
	}

	public void testSetAsTextString() {
		editor.setAsText("5");
		assertSame(c1, editor.getValue());
	}

}
