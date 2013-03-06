/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.index;

import gov.nih.nci.cabig.caaers.domain.index.ResearchStaffIndex;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.transaction.annotation.Transactional;

public class ResearchStaffIndexDao extends AbstractIndexDao {

    @Override
    public String entityIdColumnName() {
        return "researchstaff_id";
    }

    @Override
    public String indexTableName() {
        return "researchstaff_index";
    }

    @Override
    public String sequenceName() {
        return "seq_researchstaff_index_id";
    }

}
