/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.index;

import gov.nih.nci.cabig.caaers.domain.index.InvestigatorIndex;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.transaction.annotation.Transactional;

public class InvestigatorIndexDao extends AbstractIndexDao {



    @Override
    public String entityIdColumnName() {
        return "investigator_id";
    }

    @Override
    public String indexTableName() {
        return "investigator_index";
    }

    @Override
    public String sequenceName() {
        return "seq_investigator_index_id";
    }
      
	

}
