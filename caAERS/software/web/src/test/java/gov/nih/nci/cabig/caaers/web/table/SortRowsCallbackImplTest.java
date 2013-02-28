/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.table;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Lab;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableModelImpl;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.Sort;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class SortRowsCallbackImplTest extends AbstractTestCase {
    SortRowsCallbackImpl callback;
    TableModel model;
    Limit limit;
    Sort sort;
    Collection rows;
    List<AdverseEvent> events;
    AdverseEvent ae1, ae2, ae3, ae4;
    public void setUp() throws Exception {
       callback = new SortRowsCallbackImpl();
       limit = registerMockFor(Limit.class);
       model =  registerMockFor(TableModel.class);
       rows = Arrays.asList(new Grade[]{
            Grade.LIFE_THREATENING, Grade.MILD, Grade.MODERATE, Grade.NORMAL, Grade.NOT_EVALUATED, Grade.SEVERE  , Grade.DEATH
       });

         ae1 = Fixtures.createAdverseEvent(1, Grade.MILD);
        ae2 = Fixtures.createAdverseEvent(2, Grade.DEATH);
        ae3 = Fixtures.createAdverseEvent(3, Grade.NORMAL);
        ae4 = Fixtures.createAdverseEvent(4, Grade.NOT_EVALUATED);

       events = Arrays.asList(new AdverseEvent[]{ae1, ae2, ae3, ae4});
        
    }

    public void testSortRows() throws Exception {
        sort = new Sort("displayName", "displayName", TableConstants.SORT_ASC);
        EasyMock.expect(limit.getSort()).andReturn(sort).anyTimes();
        EasyMock.expect(model.getLimit()).andReturn(limit).anyTimes();
        EasyMock.expect(limit.isSorted()).andReturn(true).anyTimes();
        replayMocks();
        Object[] sortedGrades = callback.sortRows(model, rows).toArray();
        assertSame(Grade.DEATH, sortedGrades[0]);
        assertSame(Grade.LIFE_THREATENING, sortedGrades[1]);

        verifyMocks();
    }


    public void testSortRowsDescnding() throws Exception {
        sort = new Sort("displayName", "displayName", TableConstants.SORT_DESC);
        EasyMock.expect(limit.getSort()).andReturn(sort).anyTimes();
        EasyMock.expect(model.getLimit()).andReturn(limit).anyTimes();
        EasyMock.expect(limit.isSorted()).andReturn(true).anyTimes();
        replayMocks();
        Object[] sortedGrades = callback.sortRows(model, rows).toArray();
        assertSame(Grade.SEVERE, sortedGrades[0]);
        assertSame(Grade.NOT_EVALUATED, sortedGrades[1]);

        verifyMocks();

    }


    public void testSortRowsDescndingCollection() throws Exception {
        sort = new Sort("Event Grades", "grade.displayName", TableConstants.SORT_DESC);
        EasyMock.expect(limit.getSort()).andReturn(sort).anyTimes();
        EasyMock.expect(model.getLimit()).andReturn(limit).anyTimes();
        EasyMock.expect(limit.isSorted()).andReturn(true).anyTimes();
        replayMocks();
        Object[] sortedGrades = callback.sortRows(model, events).toArray();
        assertSame(ae4, sortedGrades[0]);

        verifyMocks();

    }


    public void testSortRowsAscendingCollection() throws Exception {
        sort = new Sort("Event Grades", "grade.displayName", TableConstants.SORT_ASC);
        EasyMock.expect(limit.getSort()).andReturn(sort).anyTimes();
        EasyMock.expect(model.getLimit()).andReturn(limit).anyTimes();
        EasyMock.expect(limit.isSorted()).andReturn(true).anyTimes();
        replayMocks();
        Object[] sortedGrades = callback.sortRows(model, events).toArray();
        assertSame(ae2, sortedGrades[0]);


        verifyMocks();

    }
}
