package gov.nih.nci.cabig.caaers.dao.index;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.index.OrganizationIndex;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.transaction.annotation.Transactional;

public class OrganizationIndexDao extends AbstractIndexDao {

    @Override
    public String entityIdColumnName() {
        return "organization_id";
    }

    @Override
    public String indexTableName() {
        return "organization_index";
    }

    @Override
    public String sequenceName() {
        return "seq_organization_index_id";
    }

	@Override
	public String entityTableName() {
		return "organizations";
	}

	@Override
	public String getIdColumnFromEntity() {
		return "id";
    }

}
