package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.CtcGrade;

import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class CtcTermDaoTest extends DaoTestCase<CtcTermDao> {
    public void testGetById() throws Exception {
        CtcTerm loaded = getDao().getById(3001);
        assertNotNull(loaded);
        assertEquals("Wrong category", 301, (int) loaded.getCategory().getId());
        assertEquals("Wrong CTC version", 3, (int) loaded.getCategory().getCtc().getId());
        assertTrue("Other is required", loaded.isOtherRequired());
    }

    public void testGetBySubnameMatchesTermSubstring() throws Exception {
        List<CtcTerm> matches = getDao().getBySubname(new String[] { "dry" }, 2, null);
        assertEquals("Wrong number of matches", 1, matches.size());
        assertEquals("Wrong match", 2002, (int) matches.get(0).getId());
    }

    public void testGetBySubnameMatchesCtepTermSubstring() throws Exception {
        List<CtcTerm> matches = getDao().getBySubname(new String[] { "thermal" }, 2, null);
        assertEquals("Wrong number of matches", 1, matches.size());
        assertEquals("Wrong match", 2001, (int) matches.get(0).getId());
    }

    public void testGetBySubnameMatchesSelectSubstring() throws Exception {
        List<CtcTerm> matches = getDao().getBySubname(new String[] { "chemorad" }, 3, null);
        assertEquals("Wrong number of matches", 1, matches.size());
        assertEquals("Wrong match", 3004, (int) matches.get(0).getId());
    }

    public void testGetBySubnameMatchesFiltersByCategory() throws Exception {
        List<CtcTerm> unfilteredMatches = getDao().getBySubname(new String[] { "skin" }, 3, null);
        assertEquals("Test setup invalid: wrong number of matches w/o category filter",
            2, unfilteredMatches.size());

        List<CtcTerm> matches = getDao().getBySubname(new String[] { "skin" }, 3, 302);
        assertEquals("Wrong number of matches", 1, matches.size());
        assertEquals("Wrong match", 3010, (int) matches.get(0).getId());
    }

    public void testGetContextualGrades() throws Exception {
        CtcTerm term = getDao().getById(3010);
        assertEquals("Wrong number of contextual grades", 2, term.getContextualGrades().size());
        assertEquals("Wrong 0th grade", 30103, (int) term.getContextualGrades().get(0).getId());
        CtcGrade actual = term.getContextualGrades().get(1);
        assertEquals("Wrong 1st grade", 30104, (int) actual.getId());
        assertEquals("Wrong grade", Grade.LIFE_THREATENING, actual.getGrade());
        assertEquals("Wrong text", "On fire", actual.getText());
    }
}
