package gov.nih.nci.cabig.caaers.dao.index;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.index.AdverseEventIndex;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.transaction.annotation.Transactional;

public class AdverseEventIndexDao extends AbstractIndexDao {


    @Override
    public String entityIdColumnName() {
        return "adverseevent_id";
    }

    @Override
    public String indexTableName() {
        return "adverseevent_index";
    }

    @Override
    public String sequenceName() {
        return "seq_adverseevent_index_id";
    }

	@Override
	public String entityTableName() {
		return "adverse_events";
	}

	@Override
	public String getIdColumnFromEntity() {
		return "id";
    }

    
    
}
