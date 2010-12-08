package gov.nih.nci.cabig.caaers.dao.index;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.index.ExpeditedAdverseEventReportIndex;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.transaction.annotation.Transactional;

public class ExpeditedAdverseEventReportIndexDao extends AbstractIndexDao {
    
    @Override
    public String entityIdColumnName() {
        return "expedited_ae_id";
    }

    @Override
    public String indexTableName() {
        return "expedited_ae_index";  
    }

    @Override
    public String sequenceName() {
        return "seq_expedited_ae_index_id";
    }

}
