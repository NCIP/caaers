package gov.nih.nci.cabig.caaers.web.search.cell;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;

import java.util.Arrays;

/**
 * @author Saurabh Agrawal
 * @crated Oct 2, 2008
 */
public class SelectedStudySiteCellTest extends AbstractCellTestCase {

    private SelectedStudySiteCell selectedStudySiteCell;
    private StudySearchableAjaxableDomainObject study;
    private StudySiteAjaxableDomainObject nciStudySite, ctepStudySite;

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        selectedStudySiteCell = new SelectedStudySiteCell();
        study = new StudySearchableAjaxableDomainObject();
        study.setId(1);

        nciStudySite = new StudySiteAjaxableDomainObject();
        nciStudySite.setId(1);
        nciStudySite.setName("National Cancer Inst");
        ctepStudySite = new StudySiteAjaxableDomainObject();
        ctepStudySite.setId(2);
        ctepStudySite.setName("Ctep Org");

        study.addStudySite(nciStudySite);
        study.addStudySite(ctepStudySite);


    }

    public void testDisplay() throws Exception {
        assembleTable(Arrays.asList(study));

        String cellValue = selectedStudySiteCell.getHtmlDisplay(model, column);
        assertEquals("<td><input type=\"radio\"  name=\"participant\"  id=\"participant1\"  value=\"1\" /></td>", cellValue);

    }
}