/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.index;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.transaction.annotation.Transactional;

public class ReportIndexDao extends AbstractIndexDao {

    @Override
    public String entityIdColumnName() {
        return "report_id";
    }

    @Override
    public String indexTableName() {
        return "report_index";
    }

    @Override
    public String sequenceName() {
        return "seq_report_index_id";
    }

	@Override
	public String entityTableName() {
		return "report_schedules";
	}

	@Override
	public String getIdColumnFromEntity() {
		return "id";
    }

}
