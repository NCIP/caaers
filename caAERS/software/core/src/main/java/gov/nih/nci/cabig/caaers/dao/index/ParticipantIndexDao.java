package gov.nih.nci.cabig.caaers.dao.index;

import gov.nih.nci.cabig.caaers.domain.index.ParticipantIndex;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class ParticipantIndexDao extends AbstractIndexDao {


    @Override
    public String entityIdColumnName() {
        return "participant_id";
    }

    @Override
    public String indexTableName() {
        return "participant_index";
    }

    @Override
    public String sequenceName() {
        return "seq_participant_index_id";
    }
      
	


}
