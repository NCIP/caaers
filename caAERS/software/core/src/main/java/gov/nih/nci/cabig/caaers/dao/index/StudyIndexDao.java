/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.index;

import gov.nih.nci.cabig.caaers.domain.index.StudyIndex;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.transaction.annotation.Transactional;

public class StudyIndexDao extends AbstractIndexDao {


    @Override
    public String entityIdColumnName() {
        return "study_id";
    }

    @Override
    public String indexTableName() {
        return "study_index";
    }

    @Override
    public String sequenceName() {
        return "seq_study_index_id";
    }

}
