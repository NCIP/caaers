package gov.nih.nci.cabig.caaers.domain.repository.ajax;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.query.ajax.ParticipantAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.ParticipantAjaxableDomainObject;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Biju Joseph
 */
public class ParticipantAjaxableDomainObjectRepositoryTest extends AbstractTestCase {

    private ParticipantAjaxableDomainObjectRepository participantAjaxableDomainObjectRepository;
    private HibernateTemplate hibernateTemplate;
    private ParticipantAjaxableDomainObjectQuery query;
    List<Object[]> objects = new ArrayList<Object[]>();

    Object[] participantWithNoPrimaryIdentifier = new Object[5];
    Object[] participantWithPrimaryIdentifier = new Object[5];
    Object[] participantWithNullValues = new Object[5];
    Object[] matchingParticipantWithPrimaryIdentifier = new Object[5];

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        participantAjaxableDomainObjectRepository = new ParticipantAjaxableDomainObjectRepository();

        hibernateTemplate = registerMockFor(HibernateTemplate.class);
        participantAjaxableDomainObjectRepository.setHibernateTemplate(hibernateTemplate);
        query = new ParticipantAjaxableDomainObjectQuery();

        participantWithNoPrimaryIdentifier[0] = matchingParticipantWithPrimaryIdentifier[0] = 1;
        participantWithNoPrimaryIdentifier[1] = matchingParticipantWithPrimaryIdentifier[1] = "participant first name";
        participantWithNoPrimaryIdentifier[2] = matchingParticipantWithPrimaryIdentifier[2] = "participant last name";
        participantWithNoPrimaryIdentifier[3] = "identifier value";
        participantWithNoPrimaryIdentifier[4] = Boolean.FALSE;


        participantWithPrimaryIdentifier[0] = 2;
        participantWithPrimaryIdentifier[1] = "participant first name";
        participantWithPrimaryIdentifier[2] = "participant last name";
        participantWithPrimaryIdentifier[3] = "identifier value";
        participantWithPrimaryIdentifier[4] = Boolean.TRUE;

        matchingParticipantWithPrimaryIdentifier[3] = "another identifier value";
        matchingParticipantWithPrimaryIdentifier[4] = Boolean.TRUE;

        objects.add(participantWithNoPrimaryIdentifier);
        objects.add(participantWithPrimaryIdentifier);
        objects.add(participantWithNullValues);
        objects.add(matchingParticipantWithPrimaryIdentifier);

    }

    public void testObjectArrayHasNullValues() {
        objects.clear();
        objects.add(participantWithNullValues);


        expect(hibernateTemplate.execute(isA(HibernateCallback.class))).andReturn(objects);
        replayMocks();
        List<ParticipantAjaxableDomainObject> participantAjaxableDomainObjects = participantAjaxableDomainObjectRepository.findParticipants(query);
        verifyMocks();
        assertNotNull(participantAjaxableDomainObjects);
        assertEquals(1, participantAjaxableDomainObjects.size());

        ParticipantAjaxableDomainObject participantAjaxableDomainObject = participantAjaxableDomainObjects.get(0);
        assertNull(participantAjaxableDomainObject.getId());
        assertNull(participantAjaxableDomainObject.getPrimaryIdentifierValue());
        assertNull(participantAjaxableDomainObject.getFirstName());
        assertNull(participantAjaxableDomainObject.getLastName());


    }

    public void testIfContainsObjectMethodReturnsTrue() {
        objects.clear();
        objects.add(participantWithNoPrimaryIdentifier);
        objects.add(matchingParticipantWithPrimaryIdentifier);

        assertEquals(matchingParticipantWithPrimaryIdentifier[0], participantWithNoPrimaryIdentifier[0]);
        assertEquals(matchingParticipantWithPrimaryIdentifier[1], participantWithNoPrimaryIdentifier[1]);


        expect(hibernateTemplate.execute(isA(HibernateCallback.class))).andReturn(objects);
        replayMocks();
        List<ParticipantAjaxableDomainObject> participantAjaxableDomainObjects = participantAjaxableDomainObjectRepository.findParticipants(query);
        verifyMocks();
        assertNotNull(participantAjaxableDomainObjects);
        assertEquals(1, participantAjaxableDomainObjects.size());

        ParticipantAjaxableDomainObject participantAjaxableDomainObject = participantAjaxableDomainObjects.get(0);
        assertEquals(participantWithNoPrimaryIdentifier[0], participantAjaxableDomainObject.getId());
        assertEquals(matchingParticipantWithPrimaryIdentifier[3], participantAjaxableDomainObject.getPrimaryIdentifierValue());
        assertEquals(participantWithNoPrimaryIdentifier[1], participantAjaxableDomainObject.getFirstName());
        assertEquals(participantWithNoPrimaryIdentifier[2], participantAjaxableDomainObject.getLastName());
    }

    public void testIfContainsObjectMethodReturnsTrue_AddParticipantsInReverseOrder() {
        objects.clear();
        objects.add(matchingParticipantWithPrimaryIdentifier);
        objects.add(participantWithNoPrimaryIdentifier);

        assertEquals(matchingParticipantWithPrimaryIdentifier[0], participantWithNoPrimaryIdentifier[0]);
        assertEquals(matchingParticipantWithPrimaryIdentifier[1], participantWithNoPrimaryIdentifier[1]);


        expect(hibernateTemplate.execute(isA(HibernateCallback.class))).andReturn(objects);
        replayMocks();
        List<ParticipantAjaxableDomainObject> participantAjaxableDomainObjects = participantAjaxableDomainObjectRepository.findParticipants(query);
        verifyMocks();
        assertNotNull(participantAjaxableDomainObjects);
        assertEquals(1, participantAjaxableDomainObjects.size());

        ParticipantAjaxableDomainObject participantAjaxableDomainObject = participantAjaxableDomainObjects.get(0);
        assertEquals(participantWithNoPrimaryIdentifier[0], participantAjaxableDomainObject.getId());
        assertEquals(matchingParticipantWithPrimaryIdentifier[3], participantAjaxableDomainObject.getPrimaryIdentifierValue());
        assertEquals(participantWithNoPrimaryIdentifier[1], participantAjaxableDomainObject.getFirstName());
        assertEquals(participantWithNoPrimaryIdentifier[2], participantAjaxableDomainObject.getLastName());
    }

    public void testFindParticipantHavingPrimaryIdentifier() {
        objects.clear();
        objects.add(participantWithPrimaryIdentifier);

        expect(hibernateTemplate.execute(isA(HibernateCallback.class))).andReturn(objects);
        replayMocks();
        List<ParticipantAjaxableDomainObject> participantAjaxableDomainObjects = participantAjaxableDomainObjectRepository.findParticipants(query);
        verifyMocks();
        assertNotNull(participantAjaxableDomainObjects);
        assertEquals(1, participantAjaxableDomainObjects.size());

        ParticipantAjaxableDomainObject participantAjaxableDomainObject = participantAjaxableDomainObjects.get(0);
        assertEquals(participantWithPrimaryIdentifier[0], participantAjaxableDomainObject.getId());
        assertEquals(participantWithPrimaryIdentifier[3], participantAjaxableDomainObject.getPrimaryIdentifierValue());
        assertEquals(participantWithPrimaryIdentifier[1], participantAjaxableDomainObject.getFirstName());
        assertEquals(participantWithPrimaryIdentifier[2], participantAjaxableDomainObject.getLastName());
    }

    public void testFindParticipantHavingNoPrimaryIdentifier() {
        objects.clear();
        objects.add(participantWithNoPrimaryIdentifier);

        expect(hibernateTemplate.execute(isA(HibernateCallback.class))).andReturn(objects);
        replayMocks();
        List<ParticipantAjaxableDomainObject> participantAjaxableDomainObjects = participantAjaxableDomainObjectRepository.findParticipants(query);
        verifyMocks();
        assertNotNull(participantAjaxableDomainObjects);
        assertEquals(1, participantAjaxableDomainObjects.size());

        ParticipantAjaxableDomainObject participantAjaxableDomainObject = participantAjaxableDomainObjects.get(0);
        assertNull(participantAjaxableDomainObject.getPrimaryIdentifierValue());
        assertEquals(participantWithNoPrimaryIdentifier[0], participantAjaxableDomainObject.getId());
        assertEquals(participantWithNoPrimaryIdentifier[1], participantAjaxableDomainObject.getFirstName());
        assertEquals(participantWithNoPrimaryIdentifier[2], participantAjaxableDomainObject.getLastName());
    }

    public void testFindParticipants() {
        expect(hibernateTemplate.execute(isA(HibernateCallback.class))).andReturn(objects);
        replayMocks();
        List<ParticipantAjaxableDomainObject> participantAjaxableDomainObjects = participantAjaxableDomainObjectRepository.findParticipants(query);
        verifyMocks();
        assertNotNull(participantAjaxableDomainObjects);
        assertEquals(3, participantAjaxableDomainObjects.size());


    }
}