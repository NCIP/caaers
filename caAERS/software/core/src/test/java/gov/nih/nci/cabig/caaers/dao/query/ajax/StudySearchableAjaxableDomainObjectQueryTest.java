package gov.nih.nci.cabig.caaers.dao.query.ajax;

import junit.framework.TestCase;

/**
 * @author Saurabh Agrawal
 */
public class StudySearchableAjaxableDomainObjectQueryTest extends TestCase {
	public void testTest() throws Exception {
		
	}
/*
    public void testQueryConstructor() throws Exception {
        AbstractAjaxableDomainObjectQuery query = new StudySearchableAjaxableDomainObjectQuery();

        assertEquals("wrong parsing for constructor",
                "Select study.id,study.shortTitle,identifier.value,identifier.primaryIndicator,study.phaseCode,study.status," +
                        "(select sfs.organization.nciInstituteCode from StudyFundingSponsor sfs  where sfs.study.id =study.id) as " +
                        "fundingSponsor, ss.organization.name,ss.id,ss.class,ss.organization.nciInstituteCode from Study study left join study.identifiers as identifier " +
                        "left join study.studyOrganizations as ss order by study.shortTitle", query.getQueryString());

    }

    public void testFilterByParticipantId() throws Exception {
        StudySearchableAjaxableDomainObjectQuery query = new StudySearchableAjaxableDomainObjectQuery();
        query.filterByParticipant(1);

        assertEquals("Select study.id,study.shortTitle,identifier.value,identifier.primaryIndicator,study.phaseCode,study.status," +
                "(select sfs.organization.nciInstituteCode from StudyFundingSponsor sfs  where sfs.study.id =study.id) as " +
                "fundingSponsor, ss.organization.name,ss.id,ss.class,ss.organization.nciInstituteCode from Study study left join study.identifiers as identifier " +
                "left join study.studyOrganizations as ss " +
                "left join ss.studyParticipantAssignments as spa join spa.participant as p WHERE p.id =:participantId order by study.shortTitle",
                query.getQueryString());
        assertEquals("wrong number of parameters", query.getParameterMap().size(), 1);

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "participantId"));
        assertEquals("wrong parameter value", query.getParameterMap().get("participantId"),
                Integer.valueOf(1));


    }


    public void testStudiesWithMatchingText() throws Exception {
        StudySearchableAjaxableDomainObjectQuery query = new StudySearchableAjaxableDomainObjectQuery();
        query.filterStudiesWithMatchingText("a");

        assertEquals("Select study.id,study.shortTitle,identifier.value,identifier.primaryIndicator,study.phaseCode,study.status," +
                "(select sfs.organization.nciInstituteCode from StudyFundingSponsor sfs  where sfs.study.id =study.id) as " +
                "fundingSponsor, ss.organization.name,ss.id,ss.class,ss.organization.nciInstituteCode from Study study left join study.identifiers as identifier " +
                "left join study.studyOrganizations as ss " +
                "WHERE (lower(study.shortTitle) LIKE :shortTitle or lower(study.longTitle) LIKE :longTitle " +
                "or  lower(identifier.type) LIKE :type or lower(identifier.value) LIKE :identifierValue) order by study.shortTitle",
                query.getQueryString());
        assertEquals("wrong number of parameters", query.getParameterMap().size(), 4);

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "longTitle"));
        assertEquals("wrong parameter value", query.getParameterMap().get("longTitle"),
                "%a%");

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "type"));
        assertEquals("wrong parameter value", query.getParameterMap().get("type"),
                "%a%");

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "identifierValue"));
        assertEquals("wrong parameter value", query.getParameterMap().get("identifierValue"),
                "%a%");
        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "shortTitle"));
        assertEquals("wrong parameter value", query.getParameterMap().get("shortTitle"),
                "%a%");


    }


    public void testFilterByParticipantFirstName() throws Exception {
        StudySearchableAjaxableDomainObjectQuery query = new StudySearchableAjaxableDomainObjectQuery();
        query.filterByParticipant("a", null, null, null, null, null);

        assertEquals("Select study.id,study.shortTitle,identifier.value,identifier.primaryIndicator,study.phaseCode,study.status," +
                "(select sfs.organization.nciInstituteCode from StudyFundingSponsor sfs  where sfs.study.id =study.id) as fundingSponsor, " +
                "ss.organization.name,ss.id,ss.class,ss.organization.nciInstituteCode from Study study left join study.identifiers as identifier left join " +
                "study.studyOrganizations as ss left join ss.studyParticipantAssignments as spa left join spa.participant as p " +
                "WHERE lower(p.firstName) LIKE :firstName order by study.shortTitle", query.getQueryString());
        assertEquals("wrong number of parameters", query.getParameterMap().size(), 1);

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "firstName"));
        assertEquals("wrong parameter value", query.getParameterMap().get("firstName"),
                "%a%");

    }

    public void testFilterByParticipantFirstNameAndLastName() throws Exception {
        StudySearchableAjaxableDomainObjectQuery query = new StudySearchableAjaxableDomainObjectQuery();
        query.filterByParticipant("a", "b", null, null, null, null);


        assertEquals("Select study.id,study.shortTitle,identifier.value,identifier.primaryIndicator,study.phaseCode,study.status," +
                "(select sfs.organization.nciInstituteCode from StudyFundingSponsor sfs  where sfs.study.id =study.id) as fundingSponsor, " +
                "ss.organization.name,ss.id,ss.class,ss.organization.nciInstituteCode from Study study left join study.identifiers as identifier left join " +
                "study.studyOrganizations as ss left join ss.studyParticipantAssignments as spa left join spa.participant as p WHERE " +
                "lower(p.firstName) LIKE :firstName AND lower(p.lastName) LIKE :lastName order by study.shortTitle", query.getQueryString());
        assertEquals("wrong number of parameters", query.getParameterMap().size(), 2);

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "firstName"));
        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "lastName"));
        assertEquals("wrong parameter value", query.getParameterMap().get("firstName"),
                "%a%");
        assertEquals("wrong parameter value", query.getParameterMap().get("lastName"),
                "%b%");

    }


    public void testFilterByBothParticipantAndText() throws Exception {
        StudySearchableAjaxableDomainObjectQuery query = new StudySearchableAjaxableDomainObjectQuery();
        query.filterStudiesWithMatchingShortTitleOnly("a");
        query.filterByParticipant("a", null, null, null, null, null);

        assertEquals("Select study.id,study.shortTitle,identifier.value,identifier.primaryIndicator,study.phaseCode,study.status," +
                "(select sfs.organization.nciInstituteCode from StudyFundingSponsor sfs  where sfs.study.id =study.id) as fundingSponsor, " +
                "ss.organization.name,ss.id,ss.class,ss.organization.nciInstituteCode from Study study left join study.identifiers as identifier left join " +
                "study.studyOrganizations as ss left join ss.studyParticipantAssignments as spa left join spa.participant as p " +
                "WHERE (lower(study.shortTitle) LIKE :shortTitle ) AND lower(p.firstName) LIKE :firstName order by study.shortTitle", query.getQueryString());

        assertEquals("wrong number of parameters", 2, query.getParameterMap().size());

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "shortTitle"));
        assertEquals("wrong parameter value", query.getParameterMap().get("shortTitle"),
                "%a%");

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "firstName"));
        assertEquals("wrong parameter value", query.getParameterMap().get("firstName"),
                "%a%");


    }
*/

}