package gov.nih.nci.cabig.caaers.domain.repository.ajax;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.query.ajax.StudySearchableAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Biju Joseph
 */
public class StudySearchableAjaxableDomainObjectRepositoryTest extends AbstractTestCase {

    private StudySearchableAjaxableDomainObjectRepository studySearchableAjaxableDomainObjectRepository;
    private HibernateTemplate hibernateTemplate;
    private StudySearchableAjaxableDomainObjectQuery query;
    List<Object[]> objects = new ArrayList<Object[]>();

    Object[] nciStudy = new Object[7];
    Object[] ctepStudy = new Object[7];
    Object[] studyWithNullValues = new Object[7];

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        studySearchableAjaxableDomainObjectRepository = new StudySearchableAjaxableDomainObjectRepository();

        hibernateTemplate = registerMockFor(HibernateTemplate.class);
        studySearchableAjaxableDomainObjectRepository.setHibernateTemplate(hibernateTemplate);
        query = new StudySearchableAjaxableDomainObjectQuery();

        nciStudy[0] = 1;
        nciStudy[1] = "study short title";

        nciStudy[2] = "identifier value";
        nciStudy[3] = Boolean.FALSE;
        nciStudy[4] = "Phase II Trial";
        nciStudy[5] = "Active - Trial is open to accrual";
        nciStudy[6] = "NCI";


        ctepStudy[0] = 2;
        ctepStudy[1] = "study short title";

        ctepStudy[2] = "identifier value";
        ctepStudy[3] = Boolean.TRUE;

        ctepStudy[4] = "Phase I Trial";
        ctepStudy[5] = "Active - Trial is open to accrual";

        ctepStudy[6] = "CTEP";


        objects.add(nciStudy);
        objects.add(ctepStudy);
        objects.add(studyWithNullValues);

    }

    public void ytestObjectArrayHasNullValues() {
        objects.clear();
        objects.add(studyWithNullValues);


        expect(hibernateTemplate.execute(isA(HibernateCallback.class))).andReturn(objects);
        replayMocks();
        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(query);
        verifyMocks();
        assertNotNull(studySearchableAjaxableDomainObjects);
        assertEquals(1, studySearchableAjaxableDomainObjects.size());

        StudySearchableAjaxableDomainObject studySearchableAjaxableDomainObject = studySearchableAjaxableDomainObjects.get(0);
        assertNull(studySearchableAjaxableDomainObject.getId());
        assertNull(studySearchableAjaxableDomainObject.getPrimaryIdentifierValue());
        assertNull(studySearchableAjaxableDomainObject.getShortTitle());


    }


    public void testFindStudyHavingPrimaryIdentifier() {
        objects.clear();
        objects.add(ctepStudy);

        expect(hibernateTemplate.execute(isA(HibernateCallback.class))).andReturn(objects);
        replayMocks();
        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(query);
        verifyMocks();
        assertNotNull(studySearchableAjaxableDomainObjects);
        assertEquals(1, studySearchableAjaxableDomainObjects.size());

        StudySearchableAjaxableDomainObject studySearchableAjaxableDomainObject = studySearchableAjaxableDomainObjects.get(0);
        assertEquals(ctepStudy[0], studySearchableAjaxableDomainObject.getId());
        assertEquals(ctepStudy[2], studySearchableAjaxableDomainObject.getPrimaryIdentifierValue());
        assertEquals(ctepStudy[1], studySearchableAjaxableDomainObject.getShortTitle());
        assertEquals("Active - Trial is open to accrual", studySearchableAjaxableDomainObject.getStatus());
        assertEquals("Phase I Trial", studySearchableAjaxableDomainObject.getPhaseCode());
        assertEquals("CTEP", studySearchableAjaxableDomainObject.getPrimarySponsorCode());
    }

    public void testFindStudyHavingNoPrimaryIdentifier() {
        objects.clear();
        objects.add(nciStudy);

        expect(hibernateTemplate.execute(isA(HibernateCallback.class))).andReturn(objects);
        replayMocks();
        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(query);
        verifyMocks();
        assertNotNull(studySearchableAjaxableDomainObjects);
        assertEquals(1, studySearchableAjaxableDomainObjects.size());

        StudySearchableAjaxableDomainObject studySearchableAjaxableDomainObject = studySearchableAjaxableDomainObjects.get(0);
        assertNull(studySearchableAjaxableDomainObject.getPrimaryIdentifierValue());
        assertEquals(nciStudy[0], studySearchableAjaxableDomainObject.getId());
        assertEquals(nciStudy[1], studySearchableAjaxableDomainObject.getShortTitle());
        assertEquals("Active - Trial is open to accrual", studySearchableAjaxableDomainObject.getStatus());
        assertEquals("Phase II Trial", studySearchableAjaxableDomainObject.getPhaseCode());
        assertEquals("NCI", studySearchableAjaxableDomainObject.getPrimarySponsorCode());

    }

    public void testFindStudies() {
        expect(hibernateTemplate.execute(isA(HibernateCallback.class))).andReturn(objects);
        replayMocks();
        List<StudySearchableAjaxableDomainObject> studySearchableAjaxableDomainObjects = studySearchableAjaxableDomainObjectRepository.findStudies(query);
        verifyMocks();
        assertNotNull(studySearchableAjaxableDomainObjects);
        assertEquals(3, studySearchableAjaxableDomainObjects.size());


    }
}